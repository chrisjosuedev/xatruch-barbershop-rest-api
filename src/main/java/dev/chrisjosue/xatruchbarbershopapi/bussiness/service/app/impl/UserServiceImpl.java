package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.UserService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ConflictException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import dev.chrisjosue.xatruchbarbershopapi.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        Optional<User> userExists = userRepository.findByEmailAndIsActiveIsTrue(user.getEmail());
        if (userExists.isPresent())
            throw new ConflictException("Email ya no esta disponible.", "email");
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository
                .findByEmailAndIsActiveIsTrue(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado.", "email"));
    }

    @Override
    public User update(User user) {
        var emailExists = userRepository.findByEmailAndIsActiveIsTrue(user.getEmail());

        // Email not available due to conflicts.
        if (emailExists.isPresent() && !Objects.equals(emailExists.get().getId(), user.getId())) {
            throw new ConflictException("Email no está disponible.", "email");
        }

        if (emailExists.isPresent() && Objects.equals(emailExists.get().getId(), user.getId())) {
            // User only updated his name.
            emailExists.get().setFullName(user.getFullName());
            return userRepository.save(emailExists.get());
        }

        // If email is not found, is available. Update user.
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado.", "id"));
    }
}
