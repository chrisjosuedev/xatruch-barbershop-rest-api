package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.auth;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.AuthenticationDtoResponse;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthenticationResponseMapper {
    AuthenticationResponseMapper mapper = Mappers.getMapper(AuthenticationResponseMapper.class);

    @Mapping(source = "jwt", target = "token")
    AuthenticationDtoResponse toDto(User user, String jwt);
}
