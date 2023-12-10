package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.SettingService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.MethodNotAllowedException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import dev.chrisjosue.xatruchbarbershopapi.persistance.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingRepository;

    @Override
    public GlobalSetting save(GlobalSetting globalSetting) {
        /* If it's the first time */
        if (settingRepository.findAll().isEmpty()) globalSetting.setIsActive(true);

        var currentActiveSetting = settingRepository.findGlobalSettingByIsActiveIsTrue();
        if (currentActiveSetting.isEmpty()) globalSetting.setIsActive(true);

        return settingRepository.save(globalSetting);
    }

    @Override
    public GlobalSetting update(GlobalSetting globalSetting) {
        var currentActiveSetting = settingRepository.findGlobalSettingByIsActiveIsTrue();
        if (currentActiveSetting.isEmpty()) globalSetting.setIsActive(true);
        return settingRepository.save(globalSetting);
    }

    @Override
    public GlobalSetting active(GlobalSetting globalSetting) {
        var currentActiveSetting = settingRepository.findGlobalSettingByIsActiveIsTrue();
        if (currentActiveSetting.isPresent()) {
            currentActiveSetting.get().setIsActive(false);
            settingRepository.save(currentActiveSetting.get());
        }
        return settingRepository.save(globalSetting);
    }

    @Override
    public GlobalSetting findById(Byte id) {
        return settingRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Configuración de horario con id: %s no existe.", id), "id"));
    }

    @Override
    public GlobalSetting findActiveSetting() {
        return settingRepository
                .findGlobalSettingByIsActiveIsTrue()
                .orElseThrow(() -> new ResourceNotFoundException("Configuración de horario requerida.", "settings"));
    }

    @Override
    public void remove(GlobalSetting globalSetting) {
        var currentSettingsAvailable = settingRepository.findAll();

        if (currentSettingsAvailable.size() == 1)
            throw new MethodNotAllowedException(
                    "Es necesario que exista al menos 1 configuración en el sistema, cree una nueva e intente nuevamente, o edite la disponible.",
                    "settings");

        settingRepository.delete(globalSetting);
    }

    @Override
    public List<GlobalSetting> findAll() {
        return settingRepository.findAll();
    }

    @Override
    public List<LocalTime> findActiveHours() {
        var activeSetting = findActiveSetting();
        List<LocalTime> activeHours = new LinkedList<>();
        var startTime = activeSetting.getStartDailyAvailability();
        var endTime = activeSetting.getEndDailyAvailability();
        while (startTime.isBefore(endTime) || startTime.equals(endTime)) {
            activeHours.add(startTime);
            startTime = startTime.plusHours(1);
        }
        return activeHours;
    }
}
