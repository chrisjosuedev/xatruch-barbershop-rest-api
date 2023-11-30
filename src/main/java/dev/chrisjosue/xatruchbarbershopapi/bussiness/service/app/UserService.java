package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

public interface UserService {
    void save(User user);
    User findUserByEmail(String email);
}
