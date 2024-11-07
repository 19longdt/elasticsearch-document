package sds.easywrite.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import org.springframework.security.core.Authentication;

public class AuthenticationDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Authentication authentication;

    private String token;
    private String fullName;
    private String username;
    private String role;
    private boolean isActivate;

    public AuthenticationDTO(Authentication authentication, String fullName, String username, boolean isActivate) {
        this.authentication = authentication;
        this.fullName = fullName;
        this.username = username;
        this.isActivate = isActivate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }
}
