package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.setter.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.setter.BarberSetter;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BarberSetterImpl implements BarberSetter {
    @Override
    public Person setPersonToSave(Person person) {
        person.setIsBarber(true);
        person.setIsActive(true);
        return person;
    }

    @Override
    public Person setPersonToUpdate(Person barberFound, PersonRequest personRequest) {
        barberFound.setFullName(personRequest.getFullName());
        return barberFound;
    }

    @Override
    public Person setPersonToDelete(Person barberFound) {
        barberFound.setIsActive(true);
        return barberFound;
    }
}
