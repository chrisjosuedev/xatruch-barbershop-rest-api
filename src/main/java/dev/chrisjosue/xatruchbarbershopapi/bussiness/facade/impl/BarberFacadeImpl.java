package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.BarberCases;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.BarberFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.barber.DomainToPersonDto;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.barber.PersonRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.BarberService;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.PersonDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberFacadeImpl implements BarberFacade {
    private final BarberService barberService;
    private final BarberCases barberCases;
    private final PersonRequestToDomainMapper personRequestToDomainMapper;
    private final DomainToPersonDto domainToPersonDto;

    @Override
    public PersonDto save(PersonRequest personRequest) {
        var barberDomain = personRequestToDomainMapper.toDomain(personRequest);
        var barberSet = barberCases.setPersonToSave(barberDomain);
        var barberSaved = barberService.save(barberSet);
        return domainToPersonDto.toDto(barberSaved);
    }

    @Override
    public PersonDto update(Long id, PersonRequest personRequest) {
        var barberFound = barberService.findById(id);
        var barberSet = barberCases.setPersonToUpdate(barberFound, personRequest);
        var barberUpdated = barberService.update(barberSet);
        return domainToPersonDto.toDto(barberUpdated);
    }

    @Override
    public PersonDto findById(Long id) {
        var barberFound = barberService.findById(id);
        return domainToPersonDto.toDto(barberFound);
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> allBarbers = barberService.findAll();
        return allBarbers
                .stream()
                .map(domainToPersonDto::toDto)
                .toList();
    }

    @Override
    public void remove(Long id) {
        var barberFound = barberService.findById(id);
        var barberSet = barberCases.setPersonToDelete(barberFound);
        barberService.remove(barberSet);
    }
}
