package admin.core.demo.controller;

import admin.core.demo.aopAnnotation.Login;
import admin.core.demo.aopAnnotation.Normal;
import admin.core.demo.constClass.HttpCode;
import admin.core.demo.constClass.HttpMessage;
import admin.core.demo.dto.ReturnData;
import admin.core.demo.service.StockService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Login
    @GetMapping("/index_data")
    public ResponseEntity indexData() {
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(stockService.indexData());
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
    @GetMapping("/real")
    public ResponseEntity stockReal(@RequestParam("ts_code") String tsCode) {
        ReturnData returnData = new ReturnData();
        try {
            Object o = stockService.stockReal(tsCode);
            if (o != null) {
                returnData.setData(o);
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
    @GetMapping("/list")
    public ResponseEntity stockList() {
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(stockService.stockList());
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
    @GetMapping("/daily")
    public ResponseEntity stockDailyLine(
            @RequestParam("api") String api,
            @RequestParam("ts_code") String tsCode,
            @RequestParam(name = "start_date", required = false) String startDate,
            @RequestParam(name = "end_date", required = false) String endDate
    ) {
        ReturnData returnData = new ReturnData();
        try {
            returnData.setData(stockService.dailyLine(api, tsCode, startDate, endDate));
            returnData.setCode(HttpCode.SUCCESS.getCode());
            returnData.setMsg(HttpMessage.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error(e);
            returnData.setCode(HttpCode.EXCEPTION.getCode());
            returnData.setMsg(HttpMessage.EXCEPTION.getMessage());
        }
        return ResponseEntity.ok(returnData);
    }
}
