package dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.adapter.UserAdapter;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.PersonType;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
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
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        String profileUrl = (user.getProfileUrl() == null ? DEFAULT_URL:user.getProfileUrl());

        user.setPassword(encryptedPassword);
        user.setProfileUrl(profileUrl);
        user.setRole(Role.USER);
        user.setPersonType(PersonType.SYSTEM_USER);
        user.setPasswordUpdated(true);

        return user;
    }
}
