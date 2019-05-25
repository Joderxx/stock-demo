package admin.core.demo.test;

import admin.core.demo.service.StockService;
import admin.core.demo.util.HttpUtil;
import admin.core.demo.util.ParamUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import stock.task.init.CreateTable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

    private String token = "515a3d11ee24ef9683c11ec74207832bd5a3b2cecf2ab7983f8d9220";

    @Test
    public void test1() throws IOException {
        String url = "http://api.tushare.pro";
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();

        map.put("api_name","stock_basic");
        map.put("token",token);
        map.put("param",map1);
        map.put("fields","");

        map1.put("list_status","L");
        String s = HttpUtil.post(url,JSON.toJSONString(map));
        JSONObject jsonObject = JSON.parseObject(s);
        int size = jsonObject.getJSONObject("data").getJSONArray("items").size();
        System.out.println(size);
    }

    @Test
    public void test2() {
        System.out.println(ParamUtil.getProperty("default.avatar"));
    }

    @Test
    public void test3() throws IOException {
        System.out.println(new StockService().stockReal("300033.SZ"));
    }

    @Test
    public void test4() {
        System.out.println(CreateTable.getClasses());
    }
}
