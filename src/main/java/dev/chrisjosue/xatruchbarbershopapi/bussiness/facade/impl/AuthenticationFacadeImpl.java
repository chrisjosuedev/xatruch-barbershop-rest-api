package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.auth.AuthenticationResponseMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.UserService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;
import dev.chrisjosue.xatruchbarbershopapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationResponseMapper authenticationResponseMapper;

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
}
