package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndIsActiveIsTrue(String email);
    Optional<User> findByIdAndIsActiveIsTrue(Long id);
}
