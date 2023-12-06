package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.shopservice;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ShopServiceDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToShopServiceDtoMapper {
    DomainToShopServiceDtoMapper mapper = Mappers.getMapper(DomainToShopServiceDtoMapper.class);

    ShopServiceDto toDto(ShopService shopService);
}
