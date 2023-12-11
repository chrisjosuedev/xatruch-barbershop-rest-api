package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingDetailService;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingDetailServiceImpl implements BookingDetailService {
    private final BookingDetailsRepository bookingDetailsRepository;

    @Override
    public List<BookingDetail> findAllBookingDetailByUser(Long userId, Long bookingId) {
        return bookingDetailsRepository.findBookingDetailByUser(userId, bookingId);
    }

    @Override
    public List<BookingDetail> findAllBookingDetailById(Long bookingId) {
        return bookingDetailsRepository.findBookingDetailById(bookingId);
    }
}
