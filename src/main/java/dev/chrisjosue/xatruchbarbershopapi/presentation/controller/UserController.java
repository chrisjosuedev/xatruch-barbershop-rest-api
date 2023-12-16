package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UploadFileFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.UserFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PasswordUpdateRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;
    private final AuthenticationFacade authenticationFacade;
    private final UploadFileFacade uploadFileFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        var userFound = userFacade.findById(id);
        return apiBuilder.build(200, "Usuario encontrado.", userFound, Responses.DATA);
    }

    @PostMapping("/profile-img")
    public ResponseEntity<Object> uploadProfile(Principal principal, @RequestParam("image") MultipartFile file) {
        var loggedUser = authenticationFacade.principalUser(principal);
        var imageUploaded = uploadFileFacade.uploadImage(file);
        userFacade.updateProfilePicture(imageUploaded.getUrl(), loggedUser.getId());
        return apiBuilder.build(200, "Foto de perfil subida exitosamente.", imageUploaded, Responses.DATA);
    }

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

    @PutMapping("/change-password")
    public ResponseEntity<Object> changePassword(Principal principal, @Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        var loggedUser = authenticationFacade.principalUser(principal);
        userFacade.updatePassword(loggedUser.getId(), passwordUpdateRequest);
        return apiBuilder.build(200, "Contraseña actualizada correctamente.", null, Responses.DATA);
    }
}
