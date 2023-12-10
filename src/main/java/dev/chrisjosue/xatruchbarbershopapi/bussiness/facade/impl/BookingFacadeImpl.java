package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BookingFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.booking.BookingRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.booking.DomainToBookingDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.*;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.BookingCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.BookingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
    private final BookingService bookingService;
    private final BookingCartService bookingCartService;
    private final BarberService barberService;
    private final UserService userService;
    private final SettingService settingService;
    private final BookingCases bookingCases;
    private final BookingRequestToDomainMapper bookingRequestToDomainMapper;
    private final DomainToBookingDtoMapper domainToBookingDtoMapper;

    @Override
    public List<LocalTime> findAvailability(Long barberId, Date date) {
        var barberExists = barberService.findById(barberId);
        var activeHours = settingService.findActiveHours();
        return bookingService.findAvailableTimeBarbers(barberExists.getId(), date, activeHours);
    }

    @Override
    public BookingDto bookingASession(BookingRequest bookingRequest, Long userId) {
        var barberExists = barberService.findById(bookingRequest.getBarberId());
        var userLogged = userService.findById(userId);
        var activeSetting = settingService.findActiveSetting();

        var bookingDomain = bookingRequestToDomainMapper.toDomain(bookingRequest);
        var bookingSet = bookingCases.setBookingToSave(activeSetting, bookingDomain, barberExists, userLogged);

        /* Finding Booked Services by User to Add to BookingDetails */
        var currentCart = bookingCartService.findAll(userLogged.getId());
        var bookSaved = bookingService.bookingSession(bookingSet, currentCart);

        // Map to bookingDto
        return domainToBookingDtoMapper.toDto(bookSaved);
    }
}
