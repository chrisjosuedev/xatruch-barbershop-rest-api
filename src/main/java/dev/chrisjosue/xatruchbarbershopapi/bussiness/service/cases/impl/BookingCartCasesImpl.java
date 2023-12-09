package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.BookingCartCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ConflictException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingCartCasesImpl implements BookingCartCases {
    @Override
    public BookingTempCart setBookingCartToSave(boolean exists, ShopService shopService, User user) {
        if (!exists)
            throw new ConflictException("Servicio ya fue agregado al carrito.", "barberService");

        BookingTempCart bookingTempCart = new BookingTempCart();
        bookingTempCart.setBarberService(shopService);
        bookingTempCart.setUser(user);
        return bookingTempCart;
    }

    @Override
    public BookingTempCart executeSetBookingCartToDelete(BookingTempCart bookingTempCart, User user) {
        return null;
    }
}
