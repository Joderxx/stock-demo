package admin.core.demo.util;

import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RequestUtil {

    public static final String SEPARATOR = ";";
    private static final String TOKEN = "X-Token";
    private static final String SALT = "milk";
    private static final long EXPIRE_TIME = -1;

    public static String getToken(HttpServletRequest request) {
        String header = request.getHeader(TOKEN);
        return header;
    }

    public static String getUser(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) {
            return null;
        }
        try {
            return token.split(SEPARATOR)[0];
        } catch (Exception e) {
            return null;
        }
    }

    public static String createToken(String username, List<String> roles) {
        long time = System.currentTimeMillis();
        String md5 = null;
        StringBuilder extend = new StringBuilder();
        extend.append(username).append(SEPARATOR).append(time);
        if (roles != null) {
            for (String role : roles) {
                extend.append(SEPARATOR).append(role);
            }
        }
        md5 = md5(extend.toString());
        return extend.append(SEPARATOR).append(md5).toString();
    }

    /**
     * token格式 username;timestamp;md5
     *
     * @param request
     * @return
     */
    public static boolean isAuth(HttpServletRequest request) {
        String token = getToken(request);
        return isAuth(token);
    }

    public static boolean isAuth(String token) {
        if (token == null) {
            return false;
        }
        String[] sp = token.split(SEPARATOR);
        if (sp == null || sp.length < 3) {
            return false;
        }
        long time = System.currentTimeMillis();
        try {
            if (EXPIRE_TIME >= 0 && time - Long.parseLong(sp[1]) > EXPIRE_TIME) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        StringBuilder sb = new StringBuilder(sp[0]);
        for (int i = 1; i < sp.length - 1; i++) {
            sb.append(SEPARATOR).append(sp[i]);
        }
        String md5 = md5(sb.toString());
        return md5.equals(sp[sp.length - 1]);
    }

    public static List<String> getRoles(HttpServletRequest request) {
        if (isAuth(request)) {
            String token = getToken(request);
            String[] sp = token.split(SEPARATOR);
            if (sp == null || sp.length < 4) {
                return new ArrayList<>();
            }
            List<String> list = new ArrayList<>(3);
            for (int i = 2; i < sp.length - 1; i++) {
                list.add(sp[i]);
            }
            return list;
        }
        return new ArrayList<>();
    }

    public static boolean isAdmin(HttpServletRequest request) {
        List<String> roles = getRoles(request);
        for (String role : roles) {
            if (RoleUtil.ADMIN.getName().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNormal(HttpServletRequest request) {
        List<String> roles = getRoles(request);
        for (String role : roles) {
            if (RoleUtil.NORMAL.getName().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVisitor(HttpServletRequest request) {
        List<String> roles = getRoles(request);
        for (String role : roles) {
            if (RoleUtil.VISITOR.getName().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    public static String md5(String s) {
        if (s == null) {
            s = "";
        }
        return DigestUtils.md5DigestAsHex((s + SALT).getBytes()).toLowerCase();
    }
}
