package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.booking;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.BookingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingRequestToDomainMapper {
    BookingRequestToDomainMapper mapper = Mappers.getMapper(BookingRequestToDomainMapper.class);

    @Mapping(source = "barberId", ignore = true, target = "person.id")
    Booking toDomain(BookingRequest bookingRequest);
}
