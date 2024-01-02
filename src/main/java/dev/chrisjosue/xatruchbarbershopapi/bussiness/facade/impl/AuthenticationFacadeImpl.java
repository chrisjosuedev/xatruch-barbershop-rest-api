package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.auth.AuthenticationResponseMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.DomainToUserDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.AuthenticationService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.EmailService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.UserService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.UserCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ForgotPasswordRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import dev.chrisjosue.xatruchbarbershopapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserCases userCases;
    private final AuthenticationResponseMapper authenticationResponseMapper;
    private final DomainToUserDtoMapper domainToUserDtoMapper;

    @Override
    public AuthenticationDtoResponse authenticateUser(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password)
        );
        var userFound = userService.findUserByEmail(username);
        String jwtToken = jwtService.generateToken(userFound);
        return authenticationResponseMapper.toDto(userFound, jwtToken);
    }

    @Override
    public UserDto principalUser(Principal principal) {
        var userLogged = authenticationService.getUserLoggedIn(principal);
        return domainToUserDtoMapper.toDto(userLogged);
    }

    @Override
    public void requestForgotPassword(String email) {
        var userFound = userService.findUserByEmail(email);
        String jwtRecoveryToken = jwtService.generateToken(userFound);
        emailService.sendRecoveryPasswordEmail(userFound, jwtRecoveryToken);
    }

    @Override
    public void recoveryPassword(String token, ForgotPasswordRequest forgotPasswordRequest) {
        var verifyJwt = jwtService.verifyJws(token);
        var userSubject = verifyJwt.getSubject();
        var userFound = userService.findUserByEmail(userSubject);
        var setUser = userCases.setUserToUpdatePassword(forgotPasswordRequest, userFound);
        userService.update(setUser);
    }

    @Override
    public AuthenticationDtoResponse refreshToken(String username) {
        var userLogged = userService.findUserByEmail(username);
        String jwtToken = jwtService.generateToken(userLogged);
        return authenticationResponseMapper.toDto(userLogged, jwtToken);
    }
}
