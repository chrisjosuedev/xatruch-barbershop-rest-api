package dev.chrisjosue.xatruchbarbershopapi.bussiness.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;

import java.time.LocalTime;
import java.util.List;

public interface SettingCases {
    GlobalSetting setSettingToSave(GlobalSetting globalSetting);
    GlobalSetting setDefaultSetting(GlobalSetting globalSetting);
    GlobalSetting setSettingToUpdate(SettingRequest settingRequest, GlobalSetting globalSetting);
    void executeValidationToDelete(GlobalSetting globalSetting);
}
