package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ShopServiceRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;

public interface ShopServiceCases {
    ShopService setToSaveShopService(ShopService shopService);
    ShopService setToUpdateShopService(ShopService shopService, ShopServiceRequest shopServiceRequest);
    ShopService executeSetToDeleteShopService(ShopService shopService);
    ShopService executeSetToDiscontinueShopService(ShopService shopServiceFound);
}
