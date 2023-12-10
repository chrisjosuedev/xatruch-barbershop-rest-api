package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.SettingDto;

import java.time.LocalTime;
import java.util.List;

public interface SettingFacade {
    SettingDto save(SettingRequest settingRequest);
    SettingDto update(Byte id, SettingRequest settingRequest);
    SettingDto active(Byte id);
    List<SettingDto> findAll();
    void remove(Byte id);
}
