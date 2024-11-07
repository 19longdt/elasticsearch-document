package sds.easywrite.config;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sds.easywrite.domain.User;
import sds.easywrite.repositories.UserRepository;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        User user = userRepository
            .findOneByUsernameActive(lowercaseLogin)
            .orElseThrow(() -> new UserNameNotFoundExceptionCustom("USER_NOT_FOUND"));
        if (!Objects.equals(user.getStatus(), 1)) {
            throw new UserNotActivatedException("ACCOUNT_NOT_ACTIVE");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of());
    }
}
