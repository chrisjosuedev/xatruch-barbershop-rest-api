package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.setting;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingRequestToDomainMapper {
    SettingRequestToDomainMapper mapper = Mappers.getMapper(SettingRequestToDomainMapper.class);
    GlobalSetting toDomain(SettingRequest settingRequest);
}
