package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.PersonDto;

import java.util.List;

public interface BarberFacade {
    PersonDto save(PersonRequest personRequest);
    PersonDto update(Long id, PersonRequest personRequest);
    void remove(Long id);
    PersonDto findById(Long id);
    List<PersonDto> findAll();
}
