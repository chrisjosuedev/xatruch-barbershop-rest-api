package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;

import java.util.List;

public interface BookingDetailService {
    List<BookingDetail> findAllBookingDetailByUser(Long userId, Long bookingId);
    List<BookingDetail> findAllBookingDetailById(Long bookingId);
}
