package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.auth.AuthenticationResponseMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user.DomainToUserDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.AuthenticationService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.UserService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.TokenDto;
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
    private final AuthenticationService authenticationService;
    private final UserService userService;
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
    public AuthenticationDtoResponse renewTokenWithUpdatedUser(String username) {
        var userLogged = userService.findUserByEmail(username);
        String jwtToken = jwtService.generateToken(userLogged);
        return authenticationResponseMapper.toDto(userLogged, jwtToken);
    }

    @Override
    public TokenDto refreshToken(Principal principal) {
        var userLogged = authenticationService.getUserLoggedIn(principal);
        String jwtToken = jwtService.generateToken(userLogged);
        return new TokenDto(jwtToken);
    }
}
