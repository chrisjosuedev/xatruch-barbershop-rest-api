package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.user;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.UserRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRequestToDomainMapper {
    UserRequestToDomainMapper mapper = Mappers.getMapper(UserRequestToDomainMapper.class);
    User authUserToDomain(UserRequest userRequest);
}
