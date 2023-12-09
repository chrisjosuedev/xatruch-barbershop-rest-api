package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BookingCartFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.bookingcart.DomainToBookingCartDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingCartService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.ShopServiceService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.UserService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.BookingCartCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingCartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingCartFacadeImpl implements BookingCartFacade {
    private final BookingCartService bookingCartService;
    private final UserService userService;
    private final ShopServiceService shopService;
    private final BookingCartCases bookingCartCases;
    private final DomainToBookingCartDtoMapper domainToBookingCartDtoMapper;

    @Override
    public BookingCartDto save(Long serviceId, Long userId) {
        var barberServiceFound = shopService.findById(serviceId);
        var userFound = userService.findById(userId);
        var existsInCurrentUserCart = bookingCartService.findServiceInCart(barberServiceFound.getId(), userFound.getId());
        var setServiceToCart = bookingCartCases.setBookingCartToSave(existsInCurrentUserCart, barberServiceFound, userFound);
        var bookingCartSaved = bookingCartService.save(setServiceToCart);
        return domainToBookingCartDtoMapper.toDto(bookingCartSaved);
    }

    @Override
    public List<BookingCartDto> findAllCurrentCart(Long userId) {
        return bookingCartService.findAll(userId)
                .stream()
                .map(domainToBookingCartDtoMapper::toDto)
                .toList();
    }

    @Override
    public void deleteAllCurrentCart(Long userId) {
        bookingCartService.removeAll(userId);
    }

    @Override
    public void deleteServiceFromCart(Long serviceId, Long userId) {
        var barberServiceFound = shopService.findById(serviceId);
        var userFound = userService.findById(userId);
        var bookingCartFound = bookingCartService.getServiceInCart(barberServiceFound.getId(), userFound.getId());
        bookingCartService.remove(bookingCartFound);
    }
}
