package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PasswordUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    /*
     * TODO:
     *  (x) update(UserUpdateRequest userUpdate) - PUT
     *  () updatePassword(PasswordRequest passwordRequest) - PUT
     *  () remove(Principal) - DELETE
     *  () loadPicture(Image) - POST
     */
    @PutMapping
    public ResponseEntity<Object> update(Principal principal, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        var loggedUser = authenticationFacade.principalUser(principal);
        var userUpdated = userFacade.update(loggedUser.getId(), userUpdateRequest);

        if (!loggedUser.getEmail().equals(userUpdated.getEmail())) {
            var renewedCredentials = authenticationFacade.renewTokenWithUpdatedUser(userUpdated.getEmail());
            return apiBuilder.build(
                    200,
                    "Email del usuario actualizado, refresh token generado.",
                    renewedCredentials,
                    Responses.DATA);
        }

        return apiBuilder.build(200, "Datos de usuario actualizados.", userUpdated, Responses.DATA);
    }

    @PutMapping
    public ResponseEntity<Object> changePassword(Principal principal, @Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        var loggedUser = authenticationFacade.principalUser(principal);
        userFacade.updatePassword(loggedUser.getId(), passwordUpdateRequest);
        return apiBuilder.build(200, "Contraseña actualizada correctamente.", null, Responses.DATA);
    }
}
