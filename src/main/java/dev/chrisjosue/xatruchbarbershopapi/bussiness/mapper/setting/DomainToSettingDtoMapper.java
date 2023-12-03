package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.setting;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.SettingDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToSettingDtoMapper {
    DomainToSettingDtoMapper mapper = Mappers.getMapper(DomainToSettingDtoMapper.class);
    SettingDto toDto(GlobalSetting globalSetting);
}
