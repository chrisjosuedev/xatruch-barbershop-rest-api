package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.bookingcart;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingCartDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ShopServiceDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToBookingCartDtoMapper {
    DomainToBookingCartDtoMapper mapper = Mappers.getMapper(DomainToBookingCartDtoMapper.class);

    @Mappings({
            @Mapping(target = "serviceId", source = "barberService.id"),
            @Mapping(target = "serviceName", source = "barberService.serviceName"),
            @Mapping(target = "price", source = "barberService.price"),
            @Mapping(target = "userId", source = "user.id")
    })
    BookingCartDto toDto(BookingTempCart bookingTempCart);
}
