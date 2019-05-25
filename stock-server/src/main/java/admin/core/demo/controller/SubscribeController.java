package admin.core.demo.controller;

import admin.core.demo.aopAnnotation.Login;
import admin.core.demo.aopAnnotation.Normal;
import admin.core.demo.constClass.HttpCode;
import admin.core.demo.constClass.HttpMessage;
import admin.core.demo.dto.ReturnData;
import admin.core.demo.service.SubscribeService;
import admin.core.demo.util.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @Login(Normal.class)
    @GetMapping("/list")
    public ResponseEntity getSubscribe(HttpServletRequest request) {
        String username = RequestUtil.getUser(request);
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(subscribeService.subscribeList(username));
            returnData.setCode(HttpCode.SUCCESS.getCode());
            returnData.setMsg(HttpMessage.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @Login(Normal.class)
    @PostMapping("/add")
    public ResponseEntity addSubscribe(@RequestParam("ts_code") String tsCode,
                                       HttpServletRequest request) {
        String username = RequestUtil.getUser(request);
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = subscribeService.add(username, tsCode);
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
    @DeleteMapping("/delete")
    public ResponseEntity getSubscribe(@RequestParam("ts_code") String tsCode,
                                       HttpServletRequest request) {
        String username = RequestUtil.getUser(request);
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = subscribeService.delete(username, tsCode);
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

}
