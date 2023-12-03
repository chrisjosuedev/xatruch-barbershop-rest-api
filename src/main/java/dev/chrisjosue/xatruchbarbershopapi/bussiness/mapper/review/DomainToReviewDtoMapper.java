package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.review;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ReviewDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.SettingDto;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToReviewDtoMapper {
    DomainToReviewDtoMapper mapper = Mappers.getMapper(DomainToReviewDtoMapper.class);

    @Mappings({
            @Mapping(target = "user.fullName", source = "review.user.fullName"),
            @Mapping(target = "user.profileUrl", source = "review.user.profileUrl"),
    })
    ReviewDto toDto(Review review);
}
