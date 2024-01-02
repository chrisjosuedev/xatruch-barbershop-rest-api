package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ForgotPasswordRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;

import java.security.Principal;

public interface AuthenticationFacade {
    AuthenticationDtoResponse authenticateUser(String username, String password);
    AuthenticationDtoResponse refreshToken(String username);
    UserDto principalUser(Principal principal);
    void requestForgotPassword(String email);
    void recoveryPassword(String token, ForgotPasswordRequest forgotPasswordRequest);
}
