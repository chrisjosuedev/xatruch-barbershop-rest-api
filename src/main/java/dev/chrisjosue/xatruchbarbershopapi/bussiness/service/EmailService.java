package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

public interface EmailService {
    void sendRecoveryPasswordEmail(User user, String token);
    void sendBookingEmail(Booking booking);
}
