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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/bookings/orders")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingFacade bookingFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ApiBuilder apiBuilder;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Object> findAllOrders() {
        var allSessions = bookingFacade.findAllBookings();
        return apiBuilder.build(200, "Listado de Sesiones.",
                allSessions, Responses.DATA);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> findDetailByBookingId(@PathVariable("bookingId") Long bookingId) {
        var allDetailsByBookingId = bookingFacade.findBookingById(bookingId);
        return apiBuilder.build(200,
                String.format("Listado de Detalle de Sesiones de Booking con id %s.", bookingId),
                allDetailsByBookingId, Responses.DATA);
    }

    @GetMapping("/users/{bookingId}")
    public ResponseEntity<Object> findDetailByUser(Principal principal, @PathVariable("bookingId") Long bookingId) {
        var userLogged = authenticationFacade.principalUser(principal);
        var allDetailsByUser = bookingFacade.findBookingUserById(bookingId, userLogged.getId());
        return apiBuilder.build(200,
                String.format("Listado de Detalle de Sesiones de Booking con id %s.", bookingId),
                allDetailsByUser, Responses.DATA);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> findAllUserOrders(Principal principal) {
        var userLogged = authenticationFacade.principalUser(principal);
        var allUserSessions = bookingFacade.findAllBookingsByUser(userLogged.getId());
        return apiBuilder.build(200, "Listado de Sesiones del usuario.",
                allUserSessions, Responses.DATA);
    }

    @GetMapping("/availability")
    public ResponseEntity<Object> findAvailability(
            @RequestParam(name = "barberId") Long barberId,
            @RequestParam(name = "date")
            @DateTimeFormat(pattern = "dd-MM-yyyy")
            LocalDate date) {
        var availableTime = bookingFacade.findAvailability(barberId, date);
        return apiBuilder.build(200, String.format("Listado de horas disponibles para barbero con id %s", barberId),
                availableTime, Responses.DATA);
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(Principal principal, @Valid @RequestBody BookingRequest bookingRequest) {
        var userLogged = authenticationFacade.principalUser(principal);
        var serviceBooked = bookingFacade.bookingASession(bookingRequest, userLogged.getId());

        return apiBuilder.build(201, "Sesión agendada.",
                serviceBooked, Responses.DATA);
    }
}
