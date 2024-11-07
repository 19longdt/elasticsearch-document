package sds.easywrite.dto.auth;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import sds.easywrite.constants.messages.ExceptionMessages;

public class LoginVM implements Serializable {

    @NotEmpty(message = ExceptionMessages.USERNAME_NOT_BLANK)
    private String username;

    @NotEmpty(message = ExceptionMessages.PASSWORD_NOT_BLANK)
    private String password;

    public String getUsername() {
        return username != null ? username.trim() : null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password != null ? password.trim() : null;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
