package admin.core.demo.constClass;

public enum HttpMessage {

    SUCCESS("成功"), FAIL("失败"), EXCEPTION("异常"), NO_FOUNT("找不到");

    String message;

    HttpMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
