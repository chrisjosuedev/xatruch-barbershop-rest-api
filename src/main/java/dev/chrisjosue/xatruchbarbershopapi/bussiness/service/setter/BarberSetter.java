package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.setter;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;

public interface BarberSetter {
    Person setPersonToSave(Person person);

    Person setPersonToUpdate(Person barberFound, PersonRequest personRequest);
    Person setPersonToDelete(Person barberFound);
}
