package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    List<LocalTime> findAvailableTimeBarbers(Long barberId, LocalDate date, List<LocalTime> settings);
    Booking bookingSession(Booking booking, List<BookingTempCart> cart);
    List<Booking> findAll();
    List<Booking> findAllUserBookings(Long userId);
}
