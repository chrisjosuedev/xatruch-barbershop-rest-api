package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.SettingFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.setting.DomainToSettingDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.setting.SettingRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.SettingService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.SettingCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.SettingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingFacadeImpl implements SettingFacade {
    private final SettingService settingService;
    private final SettingCases settingCases;
    private final DomainToSettingDtoMapper domainToSettingDtoMapper;
    private final SettingRequestToDomainMapper settingRequestToDomainMapper;

    @Override
    public SettingDto save(SettingRequest settingRequest) {
        var settingDomain = settingRequestToDomainMapper.toDomain(settingRequest);
        var settingSet = settingCases.setSettingToSave(settingDomain);
        var settingSaved = settingService.save(settingSet);
        return domainToSettingDtoMapper.toDto(settingSaved);
    }

    @Override
    public SettingDto update(Byte id, SettingRequest settingRequest) {
        var settingFound = settingService.findById(id);
        var settingSet = settingCases.setSettingToUpdate(settingRequest, settingFound);
        var settingUpdated = settingService.update(settingSet);
        return domainToSettingDtoMapper.toDto(settingUpdated);
    }

    @Override
    public SettingDto active(Byte id) {
        var settingFound = settingService.findById(id);
        var settingSet = settingCases.setDefaultSetting(settingFound);
        var settingActiveChanged = settingService.active(settingSet);
        return domainToSettingDtoMapper.toDto(settingActiveChanged);
    }

    @Override
    public List<SettingDto> findAll() {
        return settingService.findAll()
                .stream()
                .map(domainToSettingDtoMapper::toDto)
                .toList();
    }

    @Override
    public void remove(Byte id) {
        var settingFound = settingService.findById(id);
        settingCases.executeValidationToDelete(settingFound);
        settingService.remove(settingFound);
    }
}
