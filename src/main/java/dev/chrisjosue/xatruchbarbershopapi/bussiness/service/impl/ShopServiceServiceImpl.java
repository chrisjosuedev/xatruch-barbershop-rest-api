package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.ShopServiceService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ConflictException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import dev.chrisjosue.xatruchbarbershopapi.persistance.ShopServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShopServiceServiceImpl implements ShopServiceService {
    private final ShopServiceRepository shopServiceRepository;

    @Override
    public ShopService save(ShopService barberService) {
        var shopServiceExists = shopServiceRepository
                .findByServiceNameIgnoreCaseAndIsActiveIsTrue(barberService.getServiceName());

        if (shopServiceExists.isPresent())
            throw new ConflictException("Existe un servicio con el mismo nombre.", "serviceName");

        return shopServiceRepository.save(barberService);
    }

    @Override
    public ShopService update(ShopService barberService) {
        var shopServiceExists = shopServiceRepository
                .findByServiceNameIgnoreCaseAndIsActiveIsTrue(barberService.getServiceName());

        // Service Name not available due to conflicts.
        if (shopServiceExists.isPresent() && !Objects.equals(shopServiceExists.get().getId(), barberService.getId())) {
            throw new ConflictException("Existe un servicio con el mismo nombre.", "serviceName");
        }

        if (shopServiceExists.isPresent() && Objects.equals(shopServiceExists.get().getId(), barberService.getId())) {
            // Only updated Price
            shopServiceExists.get().setPrice(barberService.getPrice());
            return shopServiceRepository.save(shopServiceExists.get());
        }

        // If Service Name is not found, is available. Update barber Service.
        return shopServiceRepository.save(barberService);
    }

    @Override
    public void remove(ShopService barberService) {
        shopServiceRepository.delete(barberService);
    }

    @Override
    public boolean discontinueService(ShopService barberService) {
        var serviceDiscontinue = shopServiceRepository.save(barberService);
        return serviceDiscontinue.getIsActive();
    }

    @Override
    public ShopService findById(Long id) {
        return shopServiceRepository.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Servicio con id %s no existe.", id), "id"));
    }

    @Override
    public List<ShopService> findAll() {
        return shopServiceRepository.findAllByIsActiveIsTrue();
    }

    @Override
    public ShopService findByIdIncludeAll(Long id) {
        return shopServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Servicio con id %s no existe.", id), "id"));

    }
}
