package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ShopServiceRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ShopServiceDto;

import java.util.List;

public interface ShopServiceFacade {
    ShopServiceDto save(ShopServiceRequest shopServiceRequest);
    ShopServiceDto update(ShopServiceRequest shopServiceRequest, Long id);
    void remove(Long id);
    List<ShopServiceDto> findAll();
    ShopServiceDto findById(Long id);
}
