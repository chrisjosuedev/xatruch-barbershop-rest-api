package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.SettingCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettingCaseImpl implements SettingCases {

    @Override
    public GlobalSetting setSettingToSave(GlobalSetting globalSetting) {
        globalSetting.setIsActive(false);
        return globalSetting;
    }

    @Override
    public GlobalSetting setDefaultSetting(GlobalSetting globalSetting) {
        globalSetting.setIsActive(true);
        return globalSetting;
    }

    @Override
    public GlobalSetting setSettingToUpdate(SettingRequest settingRequest, GlobalSetting globalSetting) {
        globalSetting.setStartDailyAvailability(settingRequest.getStartDailyAvailability());
        globalSetting.setEndDailyAvailability(settingRequest.getEndDailyAvailability());
        return globalSetting;
    }
}
