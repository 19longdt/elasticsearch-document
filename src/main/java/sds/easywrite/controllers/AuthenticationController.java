package sds.easywrite.controllers;

import static sds.easywrite.constants.messages.ExceptionMessages.*;
import static sds.easywrite.constants.messages.ResultMessages.*;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sds.easywrite.config.JWTFilter;
import sds.easywrite.config.TokenProvider;
import sds.easywrite.config.UserAuthenticationToken;
import sds.easywrite.config.UserNameNotFoundExceptionCustom;
import sds.easywrite.dto.ResultDTO;
import sds.easywrite.dto.auth.AuthInsertTokenDTO;
import sds.easywrite.dto.auth.AuthenticationDTO;
import sds.easywrite.dto.auth.LoginVM;
import sds.easywrite.dto.auth.RegisterUserRequest;
import sds.easywrite.dto.errors.BadRequestAlertException;
import sds.easywrite.dto.errors.InternalServerException;
import sds.easywrite.services.AuthenticationService;
import sds.easywrite.utils.Common;

@RestController
@RequestMapping("/api/common")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationService authenticationService;
    private final String ENTITY_MAME = "AuthenticationController";

    public AuthenticationController(
        TokenProvider tokenProvider,
        AuthenticationManagerBuilder authenticationManagerBuilder,
        AuthenticationService authenticationService
    ) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResultDTO> login(@RequestBody LoginVM loginVM) {
        //        Common.validateInput(customValidator, ENTITY_MAME, loginVM);
        return new ResponseEntity<>(getAuthResponse(loginVM), HttpStatus.OK);
    }

    private ResultDTO getAuthResponse(LoginVM loginVM) {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication;
        AuthenticationDTO authenticationDTO;
        ResultDTO resultDTO = new ResultDTO();
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            authenticationDTO = authenticationService.getAuthenticationInfo(loginVM);
        } catch (Exception ex) {
            if (ex instanceof BadCredentialsException) {
                resultDTO = new ResultDTO(BAD_CREDENTIALS, BAD_CREDENTIALS_VI);
            } else if (ex instanceof BadRequestAlertException) {
                resultDTO = new ResultDTO(ex.getMessage(), ex.getMessage());
            } else if (ex instanceof InternalServerException) {
                resultDTO = new ResultDTO(((InternalServerException) ex).getErrorKey(), ex.getMessage());
            } else if (ex instanceof UserNameNotFoundExceptionCustom || ex instanceof InternalAuthenticationServiceException) {
                resultDTO = new ResultDTO("USER_NOT_FOUND", "USER_NOT_FOUND");
            } else {
                resultDTO = new ResultDTO(EXCEPTION_ERROR, EXCEPTION_ERROR_VI);
            }
            return resultDTO;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = "";

        if (authenticationDTO.isActivate()) {
            AuthInsertTokenDTO baseAuthInsertTokenDTO = new AuthInsertTokenDTO();
            BeanUtils.copyProperties(authenticationDTO, baseAuthInsertTokenDTO);
            token = tokenProvider.createToken(baseAuthInsertTokenDTO, loginVM);
            authenticationDTO.setToken(token);
            resultDTO.setMessage(SUCCESS);
            resultDTO.setReason(LOGIN_SUCCESS_VI);
        } else {
            resultDTO.setMessage(LOGIN_ERROR);
            resultDTO.setReason(LOGIN_ERROR_VI);
        }
        authenticationDTO.setAuthentication(null);
        resultDTO.setData(authenticationDTO);
        resultDTO.setStatus(true);
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + token);
        return resultDTO;
    }

    @PostMapping("/register")
    public ResponseEntity<ResultDTO> register(@RequestBody RegisterUserRequest request) {
        //        Common.validateInput(customValidator, ENTITY_MAME, request);
        return new ResponseEntity<>(authenticationService.registerUser(request), HttpStatus.OK);
    }
}
