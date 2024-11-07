package sds.easywrite.dto.auth;

import org.springframework.security.core.Authentication;

public class AuthInsertTokenDTO {

    private Authentication authentication;
    private Integer id;

    public AuthInsertTokenDTO() {}

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
