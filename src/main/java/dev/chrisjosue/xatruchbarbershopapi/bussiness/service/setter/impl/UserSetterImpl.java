package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.setter.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.setter.UserSetter;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSetterImpl implements UserSetter {
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_URL = "https://i.stack.imgur.com/l60Hf.png";

    @Override
    public User setUserToSave(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        String profileUrl = (user.getProfileUrl() == null ? DEFAULT_URL:user.getProfileUrl());

        user.setPassword(encryptedPassword);
        user.setProfileUrl(profileUrl);
        user.setRole(Role.USER);
        user.setIsActive(true);
        user.setIsBarber(false);
        user.setIsPasswordUpdated(true);

        return user;
    }
}
