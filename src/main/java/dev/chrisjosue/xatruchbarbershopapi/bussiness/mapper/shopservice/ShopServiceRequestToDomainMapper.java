package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.shopservice;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ShopServiceRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopServiceRequestToDomainMapper {
    ShopServiceRequestToDomainMapper mapper = Mappers.getMapper(ShopServiceRequestToDomainMapper.class);
    ShopService toDomain(ShopServiceRequest shopServiceRequest);
}
