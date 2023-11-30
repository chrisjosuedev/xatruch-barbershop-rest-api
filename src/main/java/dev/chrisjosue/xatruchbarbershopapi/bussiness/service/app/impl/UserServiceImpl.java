package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.UserService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ConflictException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import dev.chrisjosue.xatruchbarbershopapi.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        Optional<User> userExists = userRepository.findByEmail(user.getEmail());
        if (userExists.isPresent())
            throw new ConflictException("Email ya no esta disponible.", "email");
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado.", "email"));
    }
}
