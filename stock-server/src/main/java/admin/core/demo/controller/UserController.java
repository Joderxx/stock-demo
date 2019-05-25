package admin.core.demo.controller;

import admin.core.demo.aopAnnotation.Admin;
import admin.core.demo.aopAnnotation.Login;
import admin.core.demo.aopAnnotation.Normal;
import admin.core.demo.constClass.HttpCode;
import admin.core.demo.constClass.HttpMessage;
import admin.core.demo.dto.ReturnData;
import admin.core.demo.dto.UserDTO;
import admin.core.demo.model.User;
import admin.core.demo.service.UserService;
import admin.core.demo.util.ImageTypeUtil;
import admin.core.demo.util.ParamUtil;
import admin.core.demo.util.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

@Log4j2
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        ReturnData returnData = new ReturnData();
        try {
            UserDTO userDTO = userService.login(username, password);
            if (userDTO != null) {
                returnData.setData(userDTO);
                returnData.setCode(HttpCode.SUCCESS.getCode());
                returnData.setMsg(HttpMessage.SUCCESS.getMessage());
            } else {
                returnData.setCode(HttpCode.FAIL.getCode());
                returnData.setMsg(HttpMessage.FAIL.getMessage());
            }

        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @GetMapping("/avatar/{username}")
    public void avatar(
            @PathVariable("username") String username,
            HttpServletResponse response
    ) {
        byte[] data = userService.userAvatar(username);
        if (data == null || data.length == 0) {
            data = getDefault();
        }
        try {
            response.setContentType(ImageTypeUtil.getType(data));
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(String username, String password) {
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = userService.register(username, password);
            if (flag) {
                returnData.setCode(HttpCode.SUCCESS.getCode());
                returnData.setMsg(HttpMessage.SUCCESS.getMessage());
            } else {
                returnData.setCode(HttpCode.FAIL.getCode());
                returnData.setMsg(HttpMessage.FAIL.getMessage());
            }
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @Login(Normal.class)
    @PostMapping("/avatar/update")
    public ResponseEntity updateAvatar(MultipartFile avatar, HttpServletRequest request) {
        String username = RequestUtil.getUser(request);
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = userService.updateAvatar(username, avatar.getBytes());
            if (flag) {
                returnData.setCode(HttpCode.SUCCESS.getCode());
                returnData.setMsg(HttpMessage.SUCCESS.getMessage());
            } else {
                returnData.setCode(HttpCode.FAIL.getCode());
                returnData.setMsg(HttpMessage.FAIL.getMessage());
            }
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @Login(Normal.class)
    @GetMapping("/user/info")
    public ResponseEntity info(String token) {
        boolean auth = RequestUtil.isAuth(token);
        ReturnData returnData = new ReturnData();
        if (!auth) {
            returnData.setCode(HttpCode.FAIL.getCode());
            returnData.setMsg(HttpMessage.FAIL.getMessage());
        }
        String username = token.split(RequestUtil.SEPARATOR)[0];
        try {
            UserDTO userDTO = userService.info(username);
            if (userDTO != null) {
                returnData.setData(userDTO);
                returnData.setCode(HttpCode.SUCCESS.getCode());
                returnData.setMsg(HttpMessage.SUCCESS.getMessage());
            } else {
                returnData.setCode(HttpCode.FAIL.getCode());
                returnData.setMsg(HttpMessage.FAIL.getMessage());
            }

        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @Login(Normal.class)
    @GetMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.ok().build();
    }

    @Login(Admin.class)
    @PutMapping("/user/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = userService.update(user);
            if (flag) {
                returnData.setCode(HttpCode.SUCCESS.getCode());
                returnData.setMsg(HttpMessage.SUCCESS.getMessage());
            } else {
                returnData.setCode(HttpCode.FAIL.getCode());
                returnData.setMsg(HttpMessage.FAIL.getMessage());
            }
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @Login(Admin.class)
    @GetMapping("/user/list")
    public ResponseEntity userList() {
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(userService.users());
            returnData.setCode(HttpCode.SUCCESS.getCode());
            returnData.setMsg(HttpMessage.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    private byte[] getDefault() {
        String path = this.getClass().getResource("/static").getPath();
        try {
            path = URLDecoder.decode(path, "UTF-8");
            File file = new File(path, ParamUtil.getProperty("default.avatar"));
            return FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }


}

