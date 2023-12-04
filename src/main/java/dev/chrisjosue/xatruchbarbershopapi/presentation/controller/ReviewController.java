package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.ReviewFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.annotations.MaxSize;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {
    private final ReviewFacade reviewFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false, name = "approved") Boolean approved) {
        var allReviews = reviewFacade.findAll(approved);
        return apiBuilder.build(200, "Listado de reseñas de clientes.", allReviews, Responses.DATA);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/my-reviews")
    public ResponseEntity<Object> findAllUserLogged(Principal principal) {
        var userLogged = authenticationFacade.principalUser(principal);
        var allUserReviews = reviewFacade.findUserReviews(userLogged.getId());
        return apiBuilder.build(200, "Listado de reviews del usuario.", allUserReviews, Responses.DATA);
    }

    @PostMapping
    public ResponseEntity<Object> save(Principal principal, @Valid @RequestBody ReviewRequest reviewRequest) {
        var userLogged = authenticationFacade.principalUser(principal);
        var reviewSaved = reviewFacade.save(reviewRequest, userLogged.getId());
        return apiBuilder.build(201, "Review creada.", reviewSaved, Responses.DATA);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(Principal principal, @PathVariable("id") Long id, @Valid @RequestBody ReviewRequest reviewRequest) {
        var userLogged = authenticationFacade.principalUser(principal);
        var reviewUpdated = reviewFacade.update(id, reviewRequest, userLogged.getId());
        return apiBuilder.build(200, "Review actualizada.", reviewUpdated, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/approve")
    public ResponseEntity<Object> approveReviews(
            @RequestParam(name = "ids")
            @NotEmpty(message = "Lista de elementos a aprovar requerida.")
            @MaxSize(message = "Límite de reviews para aprovar es de 5.")
            Set<Long> ids) {
        var approvedReviews = reviewFacade.approveReviews(ids);
        return apiBuilder.build(200, "Reviews aprovadas.", approvedReviews, Responses.DATA);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(Principal principal, @PathVariable("id") Long id) {
        var userLogged = authenticationFacade.principalUser(principal);
        reviewFacade.remove(id, userLogged.getId());
        return apiBuilder.build(200, "Review eliminada.", null, Responses.DATA);
    }
}
