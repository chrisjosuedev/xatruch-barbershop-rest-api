package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.errors;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ErrorDetailResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ErrorToDtoMapper {
    ErrorToDtoMapper mapper = Mappers.getMapper(ErrorToDtoMapper.class);

    ErrorDetailResponseDto errorToDto(String field, String message);
}
