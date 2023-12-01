package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.UserCases;
import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCasesImpl implements UserCases {
    private final PasswordEncoder passwordEncoder;

    @Value("${values.DEFAULT_PROFILE_PICTURE}")
    private String DEFAULT_PROFILE_PICTURE;

    @Override
    public User setUserToSave(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);
        user.setProfileUrl(DEFAULT_PROFILE_PICTURE);
        user.setRole(Role.USER);
        user.setIsActive(true);
        user.setIsBarber(false);
        user.setIsPasswordUpdated(true);

        return user;
    }
}
