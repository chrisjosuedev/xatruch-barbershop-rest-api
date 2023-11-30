package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByIsBarberIsTrueAndIsActiveIsTrue();
    Optional<Person> findByIdAndIsBarberIsTrueAndIsActiveIsTrue(Long id);
}
