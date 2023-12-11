package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.LoginRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final UserFacade userFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping("/refresh-token")
    public ResponseEntity<Object> signIn(Principal principal) {
        var renewedToken = authenticationFacade.refreshToken(principal);
        return apiBuilder.build(
                200,
                "Token renovado.",
                renewedToken,
                Responses.DATA
        );
    }

    @GetMapping("/forgot-password/request")
    public ResponseEntity<Object> request(
            @RequestParam("email")
            @Email(message = "Email es inválido.")
            String email) {
        authenticationFacade.requestForgotPassword(email);
        return apiBuilder.build(
                200,
                "Petición de Cambio de Contraseña, revise el email.",
                null,
                Responses.DATA
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody UserRequest userRequest) {
        userFacade.signUp(userRequest);
        var authenticatedUser = authenticationFacade.authenticateUser(userRequest.getEmail(), userRequest.getPassword());
        return apiBuilder.build(
                201,
                "Usuario registrado con éxito.",
                authenticatedUser,
                Responses.DATA
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        var authenticatedUser = authenticationFacade.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        return apiBuilder.build(
                200,
                "Usuario logeado con éxito.",
                authenticatedUser,
                Responses.DATA
        );
    }

}
