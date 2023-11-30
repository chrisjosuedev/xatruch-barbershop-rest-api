package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.BarberService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements BarberService {
    private final BarberRepository barberRepository;

    @Override
    public Person save(Person person) {
        return barberRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return barberRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return barberRepository.findByIdAndIsBarberIsTrueAndIsActiveIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exists.", "id"));
    }

    @Override
    public List<Person> findAll() {
        return barberRepository.findAllByIsBarberIsTrueAndIsActiveIsTrue();
    }
}
