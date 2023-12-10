package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;


public interface BookingCases {
    Booking setBookingToSave(GlobalSetting activeSetting, Booking booking, Person barber, User user);
}
