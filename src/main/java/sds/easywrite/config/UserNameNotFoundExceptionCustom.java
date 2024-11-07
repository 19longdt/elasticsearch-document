package sds.easywrite.config;

import org.springframework.security.core.AuthenticationException;

public class UserNameNotFoundExceptionCustom extends AuthenticationException {

    public UserNameNotFoundExceptionCustom(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNameNotFoundExceptionCustom(String msg) {
        super(msg);
    }
}
