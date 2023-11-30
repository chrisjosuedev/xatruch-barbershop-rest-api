package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.barber;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.PersonDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToPersonDto {
    DomainToPersonDto mapper = Mappers.getMapper(DomainToPersonDto.class);

    PersonDto toDto(Person person);
}
