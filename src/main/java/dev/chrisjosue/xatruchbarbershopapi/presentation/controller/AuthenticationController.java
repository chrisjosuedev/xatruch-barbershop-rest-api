package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.LoginRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserFacade userFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

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
