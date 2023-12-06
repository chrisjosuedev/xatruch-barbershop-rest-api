package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/services")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class BarberServiceController {

    /*

     */
}
