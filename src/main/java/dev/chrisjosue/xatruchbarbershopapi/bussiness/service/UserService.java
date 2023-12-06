package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

public interface UserService {
    void save(User user);
    User findUserByEmail(String email);
    User update(User user);

    User findById(Long id);
}
