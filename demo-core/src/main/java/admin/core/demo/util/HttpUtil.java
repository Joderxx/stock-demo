package admin.core.demo.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {

    public static String post(String url, String requestBody, Map<String, String> header) throws IOException {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("POST");
        if (header != null) {
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
        }
        if (requestBody != null && requestBody.length() > 0) {
            String data = requestBody;
            connection.setDoOutput(true);
            DataOutputStream write = new DataOutputStream(connection.getOutputStream());
            write.write(data.getBytes());
            write.flush();
            write.close();
        }
        return readStream(connection.getInputStream());
    }

    public static String get(String url, String charset, Map<String, String> header) throws IOException {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("GET");
        if (header != null) {
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
        }
        return readString(connection.getInputStream(), charset);
    }

    public static String get(String url) throws IOException {
        return get(url, null, null);
    }

    public static String post(String url, String requestBody) throws IOException {
        return post(url, requestBody, null);
    }

    private static String readString(InputStream is, String charset) throws IOException {
        charset = charset == null ? "UTF-8" : charset;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        return sb.toString();
    }

    private static String readStream(InputStream is) throws IOException {
        return String.valueOf(JSON.parse(readString(is, null)));
    }
}
