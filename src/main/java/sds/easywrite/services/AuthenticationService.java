package sds.easywrite.services;

import static sds.easywrite.constants.messages.ExceptionMessages.*;
import static sds.easywrite.constants.messages.ResultMessages.*;

import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sds.easywrite.constants.UserConstants;
import sds.easywrite.domain.User;
import sds.easywrite.dto.ResultDTO;
import sds.easywrite.dto.auth.AuthenticationDTO;
import sds.easywrite.dto.auth.LoginVM;
import sds.easywrite.dto.auth.RegisterUserRequest;
import sds.easywrite.dto.errors.BadRequestAlertException;
import sds.easywrite.dto.errors.InternalServerException;
import sds.easywrite.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class AuthenticationService {

    private static final String ENTITY_NAME = "AuthenticationService";
    private final UserRepository userRepository;

    //    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationDTO getAuthenticationInfo(LoginVM loginVM) {
        User user = userRepository
            .findOneByUsernameActive(loginVM.getUsername())
            .orElseThrow(() -> new InternalServerException(USER_NOT_FOUND_VI, ENTITY_NAME, USER_NOT_FOUND));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), null);
        return new AuthenticationDTO(authenticationToken, user.getFullName(), user.getUsername(), true);
    }

    public ResultDTO registerUser(RegisterUserRequest request) {
        int countUserName = userRepository.countByUsernameAndStatus(request.getUsername(), UserConstants.ACTIVE);
        if (countUserName > 0) {
            throw new BadRequestAlertException(USER_NAME_EXISTS_VI, ENTITY_NAME, USER_NAME_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        String passwordRequest = request.getPassword();
        if (Strings.isNullOrEmpty(passwordRequest)) {
            passwordRequest = UserConstants.PASSWORD_DEFAULT;
        }
        //        user.setPassword(passwordEncoder.encode(passwordRequest));
        user.setStatus(UserConstants.ACTIVE);
        userRepository.save(user);
        return new ResultDTO(SUCCESS, REGISTER_SUCCESS_VI, true);
    }
}
