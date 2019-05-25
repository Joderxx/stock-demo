package admin.core.demo.util;

import java.util.HashMap;
import java.util.Map;

public class ImageTypeUtil {

    private static final String
            JPEG = "jpeg",
            PNG = "png",
            TIF = "tif",
            ICO = "ico",
            BMP = "bmp",
            GIF = "gif",
            UNKNOWN = "unknown";
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put(JPEG, "image/jpeg");
        map.put(PNG, "image/png");
        map.put(TIF, "image/tiff");
        map.put(ICO, "image/x-icon");
        map.put(BMP, "image/bmp");
        map.put(GIF, "image/gif");
        map.put(UNKNOWN, "unknown");
    }

    public static String getType(byte[] data) {
        String fourByte = get4Byte(data);
        String type = UNKNOWN;
        if (fourByte.startsWith("89504E47")) {
            type = PNG;
        } else if (fourByte.startsWith("474946")) {
            type = GIF;
        } else if (fourByte.startsWith("FFD8FF")) {
            type = JPEG;
        } else if (fourByte.startsWith("49492A00")) {
            type = TIF;
        } else if (fourByte.startsWith("00000100")) {
            type = ICO;
        } else if (fourByte.startsWith("424D")) {
            type = PNG;
        }
        return getType(type);
    }

    public static String getType(String type) {
        if (!map.containsKey(type)) {
            return map.get(UNKNOWN);
        }
        return map.get(type.toLowerCase());
    }

    private static String get4Byte(byte[] data) {
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            b[i] = data[i];
        }
        return bytesToHexString(b);
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toLowerCase();
    }
}
