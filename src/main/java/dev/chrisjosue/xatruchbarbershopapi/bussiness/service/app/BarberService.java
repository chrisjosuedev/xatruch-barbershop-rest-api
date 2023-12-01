package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import java.util.List;

public interface BarberService {
    Person save(Person person);
    Person update(Person person);
    Person findById(Long id);
    void remove(Person person);
    List<Person> findAll();
}
