package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.BookingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDetailDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingGeneralDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingFacade {
    List<LocalTime> findAvailability(Long barberId, LocalDate date);
    List<BookingGeneralDto> findAllBookings();
    List<BookingGeneralDto> findAllBookingsByUser(Long userId);
    List<BookingDetailDto> findBookingById(Long bookingId);
    List<BookingDetailDto> findBookingUserById(Long bookingId, Long userId);
    BookingDto bookingASession(BookingRequest bookingRequest, Long userId);
}
