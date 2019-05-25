package admin.core.demo.service;

import admin.core.demo.util.HttpUtil;
import admin.core.demo.util.ParamUtil;
import admin.core.demo.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockService {
    private static final String url = ParamUtil.getProperty("url");
    private static final String token = ParamUtil.getProperty("token");

    public Object stockList() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("list_status", "L");
        return query("stock_basic", map);
    }

    public Object dailyLine(String api, String tsCode, String startDate, String endDate) throws IOException {
        if (StringUtils.isEmpty(endDate)) {
            endDate = TimeUtil.getToday(TimeUtil.YYYYMMDD);
        }
        if (StringUtils.isEmpty(startDate)) {
            startDate = TimeUtil.before(TimeUtil.getDate(endDate, TimeUtil.YYYYMMDD),
                    30, TimeUtil.YYYYMMDD);
        }
        Map<String, String> map = new HashMap<>();
        map.put("ts_code", tsCode);
        map.put("start_date", startDate);
        map.put("end_date", endDate);
        return query(api, map);
    }

    /**
     * 指数信息
     *
     * @return
     */
    public Object indexData() throws Exception {
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=0000011,3990012,3990052,3990062,hsi5,djia7&sty=MPNSBAS&st=&sr=1&p=1&ps=1000&token=44c9d251add88e27b65ed86506f6e5da";
        String content = HttpUtil.get(url);
        List list = new ArrayList();
        if (content.length() > 6) {
            content = content.substring(3, content.length() - 3);
            String[] s = content.split("\",\"");
            List ls = new ArrayList();
            for (String e : s) {
                list.add(e.split(","));
            }

        }
        return list;
    }

    public Object stockReal(String tsCodes) throws IOException {
        List<Object> list = new ArrayList<>();
        if (StringUtils.isEmpty(tsCodes)) {
            return list;
        }
        Map<String, String> header = new HashMap<>();
        header.put("If-None-Match", "W/\"ICiAAdcv9Nb\"");
        for (String tsCode : tsCodes.split(",")) {
            if (StringUtils.isEmpty(tsCode)) {
                return null;
            }
            String[] ss = tsCode.toLowerCase().split("\\.");
            String url = "https://hq.sinajs.cn/etag.php?_=" + TimeUtil.getTime() + "&list=" + ss[1] + ss[0];
            String s = HttpUtil.get(url, "gbk", header);
            if (StringUtils.isEmpty(s)) {
                return null;
            }
            s = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
            ss = s.split(",");
            Map<String, Object> map = new HashMap<>();
            map.put("name", ss[0]);
            map.put("pre_close", ss[2]);
            map.put("real", ss[3]);
            map.put("ts_code", tsCode);
            list.add(map);
        }
        return list;
    }

    private Object query(String api, Map<String, String> param) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("api_name", api);
        map.put("token", token);
        map.put("params", param);
        String s = HttpUtil.post(url, JSON.toJSONString(map));
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject.get("data");
    }
}
