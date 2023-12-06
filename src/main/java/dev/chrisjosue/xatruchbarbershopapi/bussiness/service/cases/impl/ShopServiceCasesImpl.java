package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.ShopServiceCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.MethodNotAllowedException;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ShopServiceRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import org.springframework.stereotype.Component;

@Component
public class ShopServiceCasesImpl implements ShopServiceCases {
    @Override
    public ShopService setToSaveShopService(ShopService shopService) {
        shopService.setIsActive(true);
        shopService.setServiceName(shopService.getServiceName().trim());
        return shopService;
    }

    @Override
    public ShopService setToUpdateShopService(ShopService shopService, ShopServiceRequest shopServiceRequest) {
        shopService.setServiceName(shopServiceRequest.getServiceName());
        shopService.setPrice(shopServiceRequest.getPrice());
        return shopService;
    }

    @Override
    public ShopService executeSetToDeleteShopService(ShopService shopService) {
        if (!shopService.getBookingDetails().isEmpty() || !shopService.getBookingTempCarts().isEmpty())
            throw new MethodNotAllowedException("No es posible eliminar el servicio ya que afecta la integridad de los datos.", "service");
        shopService.setIsActive(false);
        return shopService;
    }

    @Override
    public ShopService executeSetToDiscontinueShopService(ShopService shopService) {
        if (!shopService.getBookingTempCarts().isEmpty())
            throw new MethodNotAllowedException("No es posible eliminar el servicio ya que afecta la integridad de los datos.", "service");

        shopService.setIsActive(!shopService.getIsActive());
        return shopService;
    }
}
