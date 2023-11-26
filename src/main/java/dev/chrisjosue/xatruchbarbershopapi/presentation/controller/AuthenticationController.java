package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.ResponseBuilder;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.LoginRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final UserFacade userFacade;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody UserRequest userRequest) {
        userFacade.signUp(userRequest);
        var authenticatedUser = authenticationFacade.authenticateUser(userRequest.getEmail(), userRequest.getPassword());
        return ResponseBuilder.responseHandler(
                201,
                "User registered successfully.",
                authenticatedUser
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        var authenticatedUser = authenticationFacade.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseBuilder.responseHandler(
                200,
                "User logged successfully.",
                authenticatedUser
        );
    }
}
