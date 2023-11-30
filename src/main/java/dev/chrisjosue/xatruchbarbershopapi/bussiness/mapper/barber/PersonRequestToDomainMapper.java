package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.barber;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.PersonRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonRequestToDomainMapper {
    PersonRequestToDomainMapper mapper = Mappers.getMapper(PersonRequestToDomainMapper.class);

    Person toDomain(PersonRequest personRequest);
}
