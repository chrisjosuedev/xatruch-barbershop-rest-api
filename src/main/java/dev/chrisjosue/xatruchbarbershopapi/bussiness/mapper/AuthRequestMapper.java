package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRequestMapper {
    AuthRequestMapper mapper = Mappers.getMapper(AuthRequestMapper.class);
    User authUserToDomain(UserRequest userRequest);
}
