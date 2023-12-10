package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;

import java.time.LocalTime;
import java.util.List;

public interface SettingService {
    GlobalSetting save(GlobalSetting globalSetting);
    GlobalSetting update(GlobalSetting globalSetting);
    GlobalSetting active(GlobalSetting globalSetting);
    GlobalSetting findById(Byte id);
    GlobalSetting findActiveSetting();
    void remove(GlobalSetting globalSetting);
    List<GlobalSetting> findAll();
    List<LocalTime> findActiveHours();
}
