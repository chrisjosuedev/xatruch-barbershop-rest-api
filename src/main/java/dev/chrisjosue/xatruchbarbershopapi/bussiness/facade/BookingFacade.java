package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.BookingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface BookingFacade {
    List<LocalTime> findAvailability(Long barberId, Date date);
    BookingDto bookingASession(BookingRequest bookingRequest, Long userId);
}
