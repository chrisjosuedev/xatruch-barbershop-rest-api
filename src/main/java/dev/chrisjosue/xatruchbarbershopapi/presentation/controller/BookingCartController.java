package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BookingCartFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/bookings/cart")
@RequiredArgsConstructor
public class BookingCartController {
    private final BookingCartFacade bookingCartFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping("/my-cart")
    public ResponseEntity<Object> findAll(Principal principal) {
        var userLogged = authenticationFacade.principalUser(principal);
        var allMyCart = bookingCartFacade.findAllCurrentCart(userLogged.getId());
        return apiBuilder.build(200, "Servicios en Carrito.", allMyCart, Responses.DATA);
    }

    @PostMapping("/my-cart")
    public ResponseEntity<Object> save(Principal principal, @RequestParam(name = "serviceId") Long serviceId) {
        var userLogged = authenticationFacade.principalUser(principal);
        var bookingServiceCartSaved = bookingCartFacade.save(serviceId, userLogged.getId());
        return apiBuilder.build(201, "Servicio agregado al carrito.", bookingServiceCartSaved, Responses.DATA);
    }

    @DeleteMapping("/my-cart/delete/{serviceId}")
    public ResponseEntity<Object> remove(Principal principal, @PathVariable("serviceId") Long serviceId) {
        var userLogged = authenticationFacade.principalUser(principal);
        bookingCartFacade.deleteServiceFromCart(serviceId, userLogged.getId());
        return apiBuilder.build(200, "Servicio eliminado del carrito.", null, Responses.DATA);
    }

    @DeleteMapping("/my-cart/delete")
    public ResponseEntity<Object> removeAll(Principal principal) {
        var userLogged = authenticationFacade.principalUser(principal);
        bookingCartFacade.deleteAllCurrentCart(userLogged.getId());
        return apiBuilder.build(200, "Carrito eliminado.", null, Responses.DATA);
    }
}
