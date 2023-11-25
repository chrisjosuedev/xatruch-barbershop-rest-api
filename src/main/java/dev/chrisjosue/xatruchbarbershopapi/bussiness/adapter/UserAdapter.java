package dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Barber;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

public interface UserAdapter {
    User setUserToSave(User user);
    Barber setBarberToSave(Barber barber);
}
