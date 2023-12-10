package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface BookingFacade {
    List<LocalTime> findAvailability(Long barberId, Date date);
}
