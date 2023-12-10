package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.booking;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDetailDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.BookingDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToBookingDtoMapper {
    DomainToBookingDtoMapper mapper = Mappers.getMapper(DomainToBookingDtoMapper.class);

    @Mappings({
            @Mapping(target = "serviceName", source = "barberService.serviceName")
    })
    BookingDetailDto toDto(BookingDetail bookingDetail);
    @Mappings({
            @Mapping(target = "user", source = "user.fullName"),
            @Mapping(target = "barber", source = "person.fullName"),
    })
    BookingDto toDto(Booking booking);
}
