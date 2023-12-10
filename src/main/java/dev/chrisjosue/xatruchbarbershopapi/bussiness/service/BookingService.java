package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface BookingService {
    List<LocalTime> findAvailableTimeBarbers(Long barberId, Date date, List<LocalTime> settings);
}
