package admin.core.demo.constClass;

public enum HttpCode {

    SUCCESS(200), FAIL(201), ERROR(600), NO_FOUND(400), EXCEPTION(500);

    int code;

    HttpCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
