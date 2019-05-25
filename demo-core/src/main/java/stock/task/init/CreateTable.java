package stock.task.init;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTable {

    private static List<String> classes;
    private static Map<Class, String> map = new HashMap<>();

    static {
        classes = getClassName("stock.task.model");
        map.put(Long.class, "bigint");
        map.put(String.class, "varchar(20)");
        map.put(Double.class, "double");
    }

    public static void run() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/?serverTimezone=UTC&characterEncoding=utf8";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();
        for (String clazz : classes) {
            Class c = Class.forName(clazz);
            Field[] fields = c.getDeclaredFields();
            String table = createColumnName(c.getSimpleName());
            String sql = "drop table if exists gmweb." + table;
            statement.executeUpdate(sql);
            sql = "create table gmweb." + table + "( %s );";
            StringBuilder sb = new StringBuilder();
            for (Field field : fields) {
                sb.append(createColumnName(field.getName()))
                        .append(" ")
                        .append(map.get(field.getType()))
                        .append(" ");
                if ("id".equalsIgnoreCase(field.getName())) {
                    sb.append("primary key auto_increment ");
                }
                sb.append(",");
            }

            String s = String.format(sql, sb.toString().substring(0, sb.length() - 1));
            System.out.println(s);

            int i = statement.executeUpdate(s);
            System.out.println(i > 0);
        }
        conn.close();
    }

    public static List<String> getClasses() {
        return classes;
    }

    private static List<String> getClassName(String packageName) {
        String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
        List<String> fileNames = getClassName(filePath, null);
        return fileNames;
    }

    private static List<String> getClassName(String filePath, List<String> className) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath(), myClassName));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }

        return myClassName;
    }

    private static String createColumnName(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] ca = name.toCharArray();
        boolean append = false;
        for (int i = ca.length - 1; i >= 0; i--) {
            if (append) {
                sb.append('_');
            }
            sb.append(ca[i]);
            append = ca[i] >= 'A' && ca[i] <= 'Z';
        }
        return sb.reverse().toString().toLowerCase();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        run();
    }
}
