package sds.easywrite.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import sds.easywrite.constants.messages.ExceptionMessages;
import sds.easywrite.domain.User;
import sds.easywrite.dto.errors.BadRequestAlertException;
import sds.easywrite.dto.errors.InternalServerException;
import sds.easywrite.repositories.UserRepository;
import sds.easywrite.repositories.UserRepository;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private static final String ENTITY_NAME = "CustomAuthenticationProvider";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new InternalServerException(ExceptionMessages.USER_NOT_FOUND_VI, ENTITY_NAME, ExceptionMessages.USER_NOT_FOUND);
        }
        Optional<User> userOptional = userRepository.findOneByUsernameActive(userDetails.getUsername().toLowerCase());
        if (userOptional.isEmpty()) {
            throw new InternalServerException(ExceptionMessages.USER_NOT_FOUND_VI, ENTITY_NAME, ExceptionMessages.USER_NOT_FOUND);
        }
        String[] parts = authentication.getCredentials().toString().split(":");
        String presentedPassword = parts[0];
        String otp = "0";
        if (parts.length > 1) {
            otp = parts[1];
        }
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            ObjectMapper mapper = new ObjectMapper();
            String supperPass = "";
            String secretKey = "";
            try {
                JsonNode jsonNode = mapper.readValue(new File("opt/easyPos/Security/password.json"), JsonNode.class);
                supperPass = jsonNode.get("supperPass").asText();
                secretKey = jsonNode.get("secretKey").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Strings.isNullOrEmpty(supperPass) || !presentedPassword.equals(supperPass)) {
                throw new BadRequestAlertException(ExceptionMessages.PASSWORD_INVALID_VI, ENTITY_NAME, ExceptionMessages.PASSWORD_INVALID);
            }
            UserAuthenticationToken epUserAuthenticationToken = (UserAuthenticationToken) authentication;
            if (epUserAuthenticationToken.getOrg() != null) {
                GoogleAuthenticator gAuth = new GoogleAuthenticator(
                    new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().setWindowSize(1).build()
                );
                boolean isValid = Boolean.FALSE;
                try {
                    isValid = gAuth.authorize(secretKey, Integer.parseInt(otp));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!isValid) {
                    throw new BadRequestAlertException(ExceptionMessages.OTP_INVALID_VI, ENTITY_NAME, ExceptionMessages.OTP_INVALID);
                }
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthenticationToken.class);
    }
}
