package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BarberFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barbers")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class BarberController {
    private final BarberFacade barberFacade;
    private final ApiBuilder apiBuilder;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Object> findAll() {
        var barberList = barberFacade.findAll();
        return apiBuilder.build(200, "Listado de barberos.", barberList, Responses.DATA);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        var barberFound = barberFacade.findById(id);
        return apiBuilder.build(200, "Barbero encontrado.", barberFound, Responses.DATA);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody PersonRequest personRequest) {
        var barberSaved = barberFacade.save(personRequest);
        return apiBuilder.build(201, "Barbero registrado con éxito.", barberSaved, Responses.DATA);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @Valid @RequestBody PersonRequest personRequest) {
        var barberUpdated = barberFacade.update(id, personRequest);
        return apiBuilder.build(200, "Barbero actualizado con éxito.", barberUpdated, Responses.DATA);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        barberFacade.remove(id);
        return apiBuilder.build(200, "Barbero eliminado con éxito.", null, Responses.DATA);
    }
}
