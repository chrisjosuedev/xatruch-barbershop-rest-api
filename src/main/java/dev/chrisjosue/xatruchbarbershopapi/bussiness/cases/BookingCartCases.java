package dev.chrisjosue.xatruchbarbershopapi.bussiness.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

public interface BookingCartCases {
    BookingTempCart setBookingCartToSave(boolean exists, ShopService shopService, User user);
}
