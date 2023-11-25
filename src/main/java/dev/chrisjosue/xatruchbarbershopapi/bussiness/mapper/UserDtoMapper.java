package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.UserDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper mapper = Mappers.getMapper(UserDtoMapper.class);
    UserDto toDto(User user);
}
