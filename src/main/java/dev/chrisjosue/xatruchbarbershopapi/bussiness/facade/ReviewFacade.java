package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ReviewDto;

import java.util.List;
import java.util.Set;

public interface ReviewFacade {
    ReviewDto save(ReviewRequest request, Long userId);
    ReviewDto update(Long id, ReviewRequest request, Long userId);
    void remove(Long id, Long userId);
    List<ReviewDto> findAll(Boolean approved);
    List<ReviewDto> approveReviews(Set<Long> ids);
    List<ReviewDto> findUserReviews(Long userId);
}
