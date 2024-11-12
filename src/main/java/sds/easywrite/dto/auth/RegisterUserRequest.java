package sds.easywrite.dto.auth;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import sds.easywrite.constants.messages.ExceptionMessages;

public class RegisterUserRequest implements Serializable {

    @NotBlank(message = ExceptionMessages.USERNAME_NOT_BLANK)
    public String username;

    //    @NotBlank(message = ExceptionMessages.PASSWORD_NOT_BLANK)
    //    @Length(message = ExceptionMessages.PASSWORD_LENGTH_INVALID)
    public String password;

    @NotBlank(message = ExceptionMessages.FULL_NAME_NOT_BLANK)
    public String fullName;

    public RegisterUserRequest(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
