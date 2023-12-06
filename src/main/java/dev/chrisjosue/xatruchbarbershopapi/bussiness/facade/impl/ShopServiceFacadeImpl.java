package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.ShopServiceFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.shopservice.DomainToShopServiceDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.shopservice.ShopServiceRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.ShopServiceService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.ShopServiceCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ShopServiceRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ShopServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopServiceFacadeImpl implements ShopServiceFacade {
    private final ShopServiceService shopService;
    private final ShopServiceCases shopServiceCases;
    private final DomainToShopServiceDtoMapper domainToShopServiceDtoMapper;
    private final ShopServiceRequestToDomainMapper shopServiceRequestToDomainMapper;

    @Override
    public ShopServiceDto save(ShopServiceRequest shopServiceRequest) {
        var shopServiceDomain = shopServiceRequestToDomainMapper.toDomain(shopServiceRequest);
        var shopServiceSet = shopServiceCases.setToSaveShopService(shopServiceDomain);
        var shopServiceSaved = shopService.save(shopServiceSet);
        return domainToShopServiceDtoMapper.toDto(shopServiceSaved);
    }

    @Override
    public ShopServiceDto update(ShopServiceRequest shopServiceRequest, Long id) {
        var shopServiceFound = shopService.findById(id);
        var shopServiceSet = shopServiceCases.setToUpdateShopService(shopServiceFound, shopServiceRequest);
        var shopServiceUpdated = shopService.update(shopServiceSet);
        return domainToShopServiceDtoMapper.toDto(shopServiceUpdated);
    }

    @Override
    public void remove(Long id) {
        var shopServiceFound = shopService.findById(id);
        var shopServiceSet = shopServiceCases.executeSetToDeleteShopService(shopServiceFound);
        shopService.remove(shopServiceSet);
    }

    @Override
    public List<ShopServiceDto> findAll() {
        return shopService.findAll()
                .stream()
                .map(domainToShopServiceDtoMapper::toDto)
                .toList();
    }

    @Override
    public ShopServiceDto findById(Long id) {
        var shopServiceFound = shopService.findById(id);
        return domainToShopServiceDtoMapper.toDto(shopServiceFound);
    }
}
