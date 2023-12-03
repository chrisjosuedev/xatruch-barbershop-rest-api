package dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.review;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.SettingRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.GlobalSetting;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewRequestToDomainMapper {
    ReviewRequestToDomainMapper mapper = Mappers.getMapper(ReviewRequestToDomainMapper.class);
    Review toDomain(ReviewRequest reviewRequest);
}
