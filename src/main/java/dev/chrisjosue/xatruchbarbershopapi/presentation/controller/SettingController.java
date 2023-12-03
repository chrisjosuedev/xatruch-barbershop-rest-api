package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.SettingFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class SettingController {
    private final ApiBuilder apiBuilder;
    private final SettingFacade settingFacade;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        var settingList = settingFacade.findAll();
        return apiBuilder.build(201, "Listado de configuración de horarios.", settingList, Responses.DATA);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody SettingRequest settingRequest) {
        var settingSaved = settingFacade.save(settingRequest);
        return apiBuilder.build(201, "Configuración de horario creada.", settingSaved, Responses.DATA);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Byte id, @Valid @RequestBody SettingRequest settingRequest) {
        var settingUpdated = settingFacade.update(id, settingRequest);
        return apiBuilder.build(201, "Configuración de horario actualizada.", settingUpdated, Responses.DATA);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Object> active(@PathVariable("id") Byte id) {
        var settingActivated = settingFacade.active(id);
        return apiBuilder.build(201, "Configuración de horario activada por default.", settingActivated, Responses.DATA);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Byte id) {
        settingFacade.remove(id);
        return apiBuilder.build(201, "Configuración de horario eliminado.", null, Responses.DATA);
    }
}
