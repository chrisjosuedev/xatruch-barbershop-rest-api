package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BookingFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BarberService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
    private final BookingService bookingService;
    private final BarberService barberService;
    private final SettingService settingService;

    @Override
    public List<LocalTime> findAvailability(Long barberId, Date date) {
        var barberExists = barberService.findById(barberId);
        var activeHours = settingService.findActiveHours();
        return bookingService.findAvailableTimeBarbers(barberExists.getId(), date, activeHours);
    }
}
