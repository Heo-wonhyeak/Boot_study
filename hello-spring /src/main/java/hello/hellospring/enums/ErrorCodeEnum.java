package hello.hellospring.enums;

public enum ErrorCodeEnum {

    CONNECTION_TIMEOUT(522, "Connection Time out Exception"),
    FILE_NOT_FOUND(523, "File Not Found Exception"),
    IOE_ERROR(524, "IOE Exception"),

    JSON_PARSE_EXCEPTION_ERROR(901, "JSON PARSE EXCEPTION ERROR"),
    STRING_ENCODING_EXCEPTION_ERROR(902, "JSON PARSE EXCEPTION ERROR"),

    SYSTEM_ERROR(999, "오류가 발생하였습니다. 잠시후 재시도 부탁드립니다.") // System Code
    ;

    int code;
    String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static String getEventErrorMessage(int code) {
        for (ErrorCodeEnum errCode : ErrorCodeEnum.values()) {
            if (code == errCode.code) {
                return errCode.msg();
            }
        }
        return null;
    }
}
