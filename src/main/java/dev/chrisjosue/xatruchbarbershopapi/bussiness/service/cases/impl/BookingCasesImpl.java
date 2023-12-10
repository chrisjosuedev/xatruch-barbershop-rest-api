package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.BookingCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.BadRequestException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class BookingCasesImpl implements BookingCases {
    @Override
    public Booking setBookingToSave(GlobalSetting activeSetting, Booking booking, Person barber, User user) {
        var initTime = activeSetting.getStartDailyAvailability();
        var endTime = activeSetting.getEndDailyAvailability();

        if (booking.getBookingTime().isBefore(initTime) || booking.getBookingTime().isAfter(endTime))
            throw new BadRequestException("Hora para reservación de sesión inválida.", "bookingTime");

        var roundedHour = roundHour(booking.getBookingTime());

        booking.setBookingTime(roundedHour);
        booking.setPerson(barber);
        booking.setUser(user);

        return booking;
    }

    private LocalTime roundHour(LocalTime currentTime) {
        int mainHour = currentTime.getHour();
        return LocalTime.of(mainHour, 0, 0);
    }
}
