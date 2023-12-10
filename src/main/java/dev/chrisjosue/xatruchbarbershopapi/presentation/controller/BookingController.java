package dev.chrisjosue.xatruchbarbershopapi.presentation.controller;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.builder.ApiBuilder;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.AuthenticationFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BookingFacade;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Responses;
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
            @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        var availableTime = bookingFacade.findAvailability(barberId, date);
        return apiBuilder.build(200, String.format("Listado de horas disponibles para barbero con id %s", barberId),
                availableTime, Responses.DATA);
    }

    @PostMapping
    public ResponseEntity<Object> findAvailability(Principal principal) {
        var userLogged = authenticationFacade.principalUser(principal);
        /**
         * Facade parse any time to a round time:
         * EX -> 18:32:93 to 18:00:00
         */
        return apiBuilder.build(201, "Session Booked.",
                null, Responses.DATA);
    }

}
