package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingCartService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingCartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingCartServiceImpl implements BookingCartService {
    private final BookingCartRepository bookingCartRepository;

    @Override
    public BookingTempCart save(BookingTempCart bookingTempCart) {
        return bookingCartRepository.save(bookingTempCart);
    }

    @Override
    public boolean findServiceInCart(Long serviceId, Long userId) {
        var exists = bookingCartRepository.findBookingTempCartByBarberServiceIdAndUserId(serviceId, userId);
        return exists.isEmpty();
    }

    @Override
    public BookingTempCart getServiceInCart(Long serviceId, Long userId) {
        return bookingCartRepository
                .findBookingTempCartByBarberServiceIdAndUserId(serviceId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no esta agregado al carrito.", "serviceId"));

    }

    @Override
    public void remove(BookingTempCart bookingTempCart) {
        bookingCartRepository.delete(bookingTempCart);
    }

    @Override
    @Transactional
    public void removeAll(Long userId) {
        bookingCartRepository.deleteAllByUserId(userId);
    }

    @Override
    public List<BookingTempCart> findAll(Long userId) {
        return bookingCartRepository.findAllByUserId(userId);
    }
}
