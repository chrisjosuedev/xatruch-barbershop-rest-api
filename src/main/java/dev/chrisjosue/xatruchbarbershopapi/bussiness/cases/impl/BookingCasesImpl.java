package dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.BookingCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.BadRequestException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class BookingCasesImpl implements BookingCases {
    @Override
    public Booking setBookingToSave(GlobalSetting activeSetting, Booking booking, Person barber, User user) {
        var initTime = activeSetting.getStartDailyAvailability();
        var endTime = activeSetting.getEndDailyAvailability();

        if (booking.getBookingTime().isBefore(initTime) || booking.getBookingTime().isAfter(endTime))
            throw new BadRequestException("Hora para reservación de sesión inválida.", "bookingTime");

        var currentDateTime = LocalDateTime.now();
        var sessionDateTime = getBookingDateTime(booking.getBookingDate(), booking.getBookingTime());

        if (sessionDateTime.isBefore(currentDateTime))
            throw new BadRequestException("Hora para reservación no puede ser antes en un horario antes que fecha actual.", "bookingTime");

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

    private LocalDateTime getBookingDateTime(Date bookingDate, LocalTime bookingTime) {
        LocalDate localDate = bookingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return bookingTime.atDate(localDate).plusDays(1);
    }
}
