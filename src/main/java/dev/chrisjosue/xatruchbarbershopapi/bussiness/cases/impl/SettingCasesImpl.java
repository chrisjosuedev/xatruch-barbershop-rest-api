package dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.SettingCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.MethodNotAllowedException;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettingCasesImpl implements SettingCases {

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

    @Override
    public void executeValidationToDelete(GlobalSetting globalSetting) {
        if (globalSetting.getIsActive())
            throw new MethodNotAllowedException(
                    String.format("La configuración con id %s se encuentra activa.", globalSetting.getId()),
                    "settings");
    }
}
