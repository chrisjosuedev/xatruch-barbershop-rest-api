package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BookingFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.BookingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/bookings/orders")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingFacade bookingFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    @GetMapping("/availability")
    public ResponseEntity<Object> findAvailability(
            @RequestParam(name = "barberId") Long barberId,
            @RequestParam(name = "date")
            @DateTimeFormat(pattern = "dd-MM-yyyy")
            Date date) {
        var availableTime = bookingFacade.findAvailability(barberId, date);
        return apiBuilder.build(200, String.format("Listado de horas disponibles para barbero con id %s", barberId),
                availableTime, Responses.DATA);
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(Principal principal, @Valid @RequestBody BookingRequest bookingRequest) {
        var userLogged = authenticationFacade.principalUser(principal);
        var serviceBooked = bookingFacade.bookingASession(bookingRequest, userLogged.getId());

        return apiBuilder.build(201, "Session Booked.",
                serviceBooked, Responses.DATA);
    }

}
