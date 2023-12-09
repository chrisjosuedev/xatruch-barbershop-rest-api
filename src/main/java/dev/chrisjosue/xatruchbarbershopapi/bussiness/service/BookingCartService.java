package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;

import java.util.List;
import java.util.Optional;

public interface BookingCartService {
    BookingTempCart save(BookingTempCart bookingTempCart);
    boolean findServiceInCart(Long serviceId, Long userId);
    BookingTempCart getServiceInCart(Long serviceId, Long userId);
    void remove(BookingTempCart bookingTempCart);
    void removeAll(Long userId);
    List<BookingTempCart> findAll(Long userId);
}
