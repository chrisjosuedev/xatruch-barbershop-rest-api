package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.ShopServiceFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ShopServiceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ShopServiceController {
    private final ShopServiceFacade shopServiceFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        var shopServiceList = shopServiceFacade.findAll();
        return apiBuilder.build(200, "Listado de servicios disponibles.", shopServiceList, Responses.DATA);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        var shopServiceFound = shopServiceFacade.findById(id);
        return apiBuilder.build(200, "Servicio Encontrado.", shopServiceFound, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ShopServiceRequest shopServiceRequest) {
        var shopCreated = shopServiceFacade.save(shopServiceRequest);
        return apiBuilder.build(201, "Servicio creado.", shopCreated, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @Valid @RequestBody ShopServiceRequest shopServiceRequest) {
        var shopServiceUpdated = shopServiceFacade.update(shopServiceRequest, id);
        return apiBuilder.build(200, "Servicio Actualizado.", shopServiceUpdated, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        shopServiceFacade.remove(id);
        return apiBuilder.build(200, "Servicio eliminado.", null, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/discontinue/{id}")
    public ResponseEntity<Object> discontinue(@PathVariable("id") Long id) {
        var operationDone = shopServiceFacade.discontinue(id);
        var displayMsg = operationDone ? "habilitado" : "descontinuado";
        return apiBuilder.build(200, "Servicio " + displayMsg + ".",
                null, Responses.DATA);
    }
}
