package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToUserDtoMapper {
    DomainToUserDtoMapper mapper = Mappers.getMapper(DomainToUserDtoMapper.class);
    UserDto toDto(User user);
}
