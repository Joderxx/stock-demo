package admin.core.demo.controller;

import admin.core.demo.aopAnnotation.Admin;
import admin.core.demo.aopAnnotation.Login;
import admin.core.demo.constClass.HttpCode;
import admin.core.demo.constClass.HttpMessage;
import admin.core.demo.dto.ReturnData;
import admin.core.demo.service.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Login(Admin.class)
    @GetMapping("/roles")
    public ResponseEntity roles() {
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(roleService.roles());
            returnData.setCode(HttpCode.SUCCESS.getCode());
            returnData.setMsg(HttpMessage.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @GetMapping({"/", ""})
    public String test() {
        return "Hi, this is a test.";
    }
}
