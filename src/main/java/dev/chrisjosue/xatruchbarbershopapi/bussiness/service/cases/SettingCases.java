package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;

public interface SettingCases {
    GlobalSetting setSettingToSave(GlobalSetting globalSetting);
    GlobalSetting setDefaultSetting(GlobalSetting globalSetting);
    GlobalSetting setSettingToUpdate(SettingRequest settingRequest, GlobalSetting globalSetting);
    void executeValidationToDelete(GlobalSetting globalSetting);
}
