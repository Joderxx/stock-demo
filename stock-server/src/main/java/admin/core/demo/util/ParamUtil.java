package admin.core.demo.util;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ParamUtil {

    private static Properties properties = new Properties();

    static {
        try (InputStream is = ParamUtil.class
                .getResourceAsStream("/param.properties")) {
            properties.load(is);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
