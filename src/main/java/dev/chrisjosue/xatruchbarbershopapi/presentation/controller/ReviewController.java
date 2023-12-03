package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.ReviewFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewFacade reviewFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false, name = "approved") Boolean approved) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Object> save(Principal principal, @Valid @RequestBody ReviewRequest reviewRequest) {
        var userLogged = authenticationFacade.principalUser(principal);
        var reviewSaved = reviewFacade.save(reviewRequest, userLogged.getId());
        return apiBuilder.build(200, "Review creada.", reviewSaved, Responses.DATA);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(Principal principal, @PathVariable("id") Long id, @Valid @RequestBody ReviewRequest reviewRequest) {
        var userLogged = authenticationFacade.principalUser(principal);
        var reviewUpdated = reviewFacade.update(id, reviewRequest, userLogged.getId());
        return apiBuilder.build(201, "Review actualizada.", reviewUpdated, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/approve")
    public ResponseEntity<Object> approveReviews(@RequestParam(name = "ids") List<Long> ids) {
        /*
        TODO: Approve Reviews (MAX 5)
         */
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(Principal principal, @PathVariable("id") Long id) {
        var userLogged = authenticationFacade.principalUser(principal);
        reviewFacade.remove(id, userLogged.getId());
        return apiBuilder.build(201, "Review eliminada.", null, Responses.DATA);
    }
}
