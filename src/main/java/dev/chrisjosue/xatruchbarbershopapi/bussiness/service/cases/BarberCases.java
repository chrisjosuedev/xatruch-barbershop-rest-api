package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;

public interface BarberCases {
    Person setPersonToSave(Person person);
    Person setPersonToUpdate(Person barberFound, PersonRequest personRequest);
    Person setPersonToDelete(Person barberFound);
}
