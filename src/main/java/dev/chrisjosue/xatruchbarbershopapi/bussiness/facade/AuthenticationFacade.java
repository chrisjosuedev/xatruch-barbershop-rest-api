package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.TokenDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

public interface AuthenticationFacade {
    AuthenticationDtoResponse authenticateUser(String username, String password);
    AuthenticationDtoResponse renewTokenWithUpdatedUser(String username);
    TokenDto refreshToken(Principal principal);
    UserDto principalUser(Principal principal);
    void requestForgotPassword(String email);
    /*
    TODO:
    recoveryPassword, check Token if is still valid, change password and return UserDTO
     */
}
