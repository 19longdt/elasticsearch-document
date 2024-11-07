package sds.easywrite.constants.messages;

public final class ExceptionMessages {

    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String BAD_REQUEST_MULTIPLE = "BAD_REQUEST_MULTIPLE";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String BAD_REQUEST_VI = "Dữ liệu gửi lên không hợp lệ";
    public static final String USER_NOT_FOUND_VI = "Tài khoản không tồn tại";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String UNAUTHORIZED_VI = "Bạn không có quyền truy cập chức năng này";
    public static final String INTERNAL_SERVER = "INTERNAL_SERVER";
    public static final String EXCEPTION_ERROR = "EXCEPTION_ERROR";
    public static final String API_NOT_FOUNT = "API_NOT_FOUNT";
    public static final String EXCEPTION_ERROR_VI = "Lỗi hệ thống";
    public static final String BAD_CREDENTIALS = "BAD_CREDENTIALS";
    public static final String BAD_CREDENTIALS_VI = "Tài khoản không hợp lệ";
    public static final String PASSWORD_INVALID = "PASSWORD_INVALID";
    public static final String PASSWORD_VALID_VI = "Mật khẩu không đúng";
    public static final String PASSWORD_INVALID_VI = "Mật khẩu không hợp lệ";
    public static final String OTP_INVALID = "OTP_INVALID";
    public static final String OTP_INVALID_VI = "Mã xác minh không hợp lệ";

    public static final String USERNAME_NOT_BLANK = "{\"code\": \"USERNAME_NOT_BLANK\",\"message\": \"Tên đăng nhập không được bỏ trống\"}";
    public static final String PASSWORD_NOT_BLANK = "{\"code\": \"PASSWORD_NOT_BLANK\",\"message\": \"Không để trống mật khẩu\"}";
    public static final String PASSWORD_LENGTH_INVALID =
        "{\"code\": \"PASSWORD_LENGTH_INVALID\",\"message\": \"Độ dài mật khẩu phải lớn hơn 6 ký tự\"}";
    public static final String FULL_NAME_NOT_BLANK = "{\"code\": \"FULL_NAME_NOT_BLANK\",\"message\": \"Không để trống tên đầy đủ\"}";
    public static final String USER_NAME_EXISTS_VI = "Tài khoản đã tồn tại";
    public static final String USER_NAME_EXISTS = "USER_NAME_EXISTS";

    private ExceptionMessages() {}
}
