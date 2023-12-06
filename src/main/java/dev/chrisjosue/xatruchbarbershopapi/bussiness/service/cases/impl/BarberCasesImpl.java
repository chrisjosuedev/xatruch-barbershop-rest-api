package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.BarberCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BarberCasesImpl implements BarberCases {
    @Override
    public Person setPersonToSave(Person person) {
        person.setFullName(person.getFullName().trim());
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
        barberFound.setIsActive(false);
        return barberFound;
    }
}
