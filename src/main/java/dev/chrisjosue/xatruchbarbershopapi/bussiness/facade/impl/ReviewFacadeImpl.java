package dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.facade.ReviewFacade;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.review.DomainToReviewDtoMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.mapper.review.ReviewRequestToDomainMapper;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.ReviewService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.UserService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.ReviewCases;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.response.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewFacadeImpl implements ReviewFacade {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ReviewCases reviewCases;
    private final DomainToReviewDtoMapper domainToReviewDtoMapper;
    private final ReviewRequestToDomainMapper reviewRequestToDomainMapper;

    @Override
    public ReviewDto save(ReviewRequest reviewRequest, Long userId) {
        var reviewDomain = reviewRequestToDomainMapper.toDomain(reviewRequest);
        var userReview = userService.findById(userId);
        var reviewSet = reviewCases.setReviewToSave(reviewDomain, userReview);

        var reviewSaved = reviewService.save(reviewSet);
        return domainToReviewDtoMapper.toDto(reviewSaved);
    }

    @Override
    public ReviewDto update(Long id, ReviewRequest reviewRequest, Long userId) {
        var reviewFound = reviewService.findById(id);
        var userReview = userService.findById(userId);

        reviewCases.executeReviewBelongsToUser(reviewFound, userReview.getId());

        var reviewSet = reviewCases.setReviewToUpdate(reviewFound, reviewRequest);
        var reviewUpdated = reviewService.update(reviewSet);
        return domainToReviewDtoMapper.toDto(reviewUpdated);
    }

    @Override
    public void remove(Long id, Long userId) {
        var reviewFound = reviewService.findById(id);
        var userReview = userService.findById(userId);

        reviewCases.executeReviewBelongsToUser(reviewFound, userReview.getId());
        reviewCases.executeValidationToDelete(reviewFound);

        var reviewSet = reviewCases.setReviewToDelete(reviewFound);

        reviewService.remove(reviewSet);
    }

    @Override
    public List<ReviewDto> findAll(Boolean approved) {
        return reviewService
                .findAll(approved)
                .stream()
                .map(domainToReviewDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<ReviewDto> approveReviews(List<Long> ids) {
        /*
        TODO: Approve List of Reviews
         */
        return null;
    }
}
