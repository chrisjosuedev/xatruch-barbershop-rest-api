package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;

import java.util.List;

public interface SettingService {
    GlobalSetting save(GlobalSetting globalSetting);
    GlobalSetting update(GlobalSetting globalSetting);
    GlobalSetting active(GlobalSetting globalSetting);
    GlobalSetting findById(Byte id);
    void remove(GlobalSetting globalSetting);
    List<GlobalSetting> findAll();
}
