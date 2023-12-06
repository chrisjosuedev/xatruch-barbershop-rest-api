package dev.chrisjosue.xatruchbarbershopapi.bussiness.service;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;

import java.util.List;

public interface ShopServiceService {
    ShopService save(ShopService barberService);
    ShopService update(ShopService barberService);
    void remove(ShopService barberService);
    boolean discontinueService(ShopService barberService);
    ShopService findById(Long id);
    List<ShopService> findAll();
    ShopService findByIdIncludeAll(Long id);
}
