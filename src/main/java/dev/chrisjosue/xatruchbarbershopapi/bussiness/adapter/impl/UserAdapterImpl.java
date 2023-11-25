package dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter.UserAdapter;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Barber;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapterImpl implements UserAdapter {
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_URL = "https://i.stack.imgur.com/l60Hf.png";

    @Override
    public User setUserToSave(User user) {
        var userClient = setGlobalUser(user);
        userClient.setRole(Role.USER_CLIENT);
        return userClient;
    }

    @Override
    public Barber setBarberToSave(Barber barber) {
        var userBarber = setGlobalUser(barber);
        userBarber.setRole(Role.USER_BARBER);
        return userBarber;
    }

    private <T extends User> T setGlobalUser(T userEntity) {
        String encryptedPassword = passwordEncoder.encode(userEntity.getPassword());
        String profileUrl = (userEntity.getProfileUrl() == null ? DEFAULT_URL:userEntity.getProfileUrl());
        userEntity.setRole(Role.USER_CLIENT);
        userEntity.setPassword(encryptedPassword);
        userEntity.setProfileUrl(profileUrl);
        return userEntity;
    }
}
