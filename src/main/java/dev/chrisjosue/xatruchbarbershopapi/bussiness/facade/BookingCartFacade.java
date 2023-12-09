package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingCartDto;

import java.util.List;

public interface BookingCartFacade {
    BookingCartDto save(Long serviceId, Long userId);
    List<BookingCartDto> findAllCurrentCart(Long userId);
    void deleteAllCurrentCart(Long userId);
    void deleteServiceFromCart(Long serviceId, Long userId);
}
