package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.SettingService;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public List<LocalTime> findAvailableTimeBarbers(Long personId, Date bookingDate, List<LocalTime> settings) {
        var barberBookings = bookingRepository.findAllByPersonIdAndBookingDate(personId, bookingDate);

        if (barberBookings.isEmpty()) return settings;

        settings.removeAll(barberBookings);
        return settings;
    }
}
