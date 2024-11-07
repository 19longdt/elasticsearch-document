package sds.easywrite.config;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Integer org;

    public UserAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
        setAuthenticated(false);
    }

    public UserAuthenticationToken(Object principal, Object credentials, Integer org) {
        super(principal, credentials);
        this.org = org;
        setAuthenticated(false);
    }

    public UserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Integer org) {
        super(principal, credentials, authorities);
        this.org = org;
        super.setAuthenticated(true); // must use super, as we override
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead"
            );
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    public Integer getOrg() {
        return org;
    }

    public void setOrg(Integer org) {
        this.org = org;
    }
}
