package admin.core.demo.controller;

import admin.core.demo.aopAnnotation.Admin;
import admin.core.demo.aopAnnotation.Login;
import admin.core.demo.aopAnnotation.Normal;
import admin.core.demo.constClass.HttpCode;
import admin.core.demo.constClass.HttpMessage;
import admin.core.demo.dto.ReturnData;
import admin.core.demo.model.Knowledge;
import admin.core.demo.service.KnowService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/knowledge")
public class KnowController {

    @Autowired
    private KnowService knowService;

    @Login(Normal.class)
    @GetMapping("/get")
    public ResponseEntity get() {
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(knowService.getAll());
            returnData.setCode(HttpCode.SUCCESS.getCode());
            returnData.setMsg(HttpMessage.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }

    @Login(Admin.class)
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Knowledge knowledge) {
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = knowService.updateOrAdd(knowledge);
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
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam("id") Integer id) {
        ReturnData returnData = new ReturnData();
        try {
            boolean flag = knowService.delete(id);
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
