package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases.ReviewCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ForbiddenException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.MethodNotAllowedException;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewCasesImpl implements ReviewCases {
    @Override
    public Review setReviewToSave(Review review, User user) {
        review.setIsActive(true);
        review.setIsApproved(false);
        review.setUser(user);
        return review;
    }

    @Override
    public Review setReviewToUpdate(Review review, ReviewRequest reviewRequest) {
        review.setTitle(reviewRequest.getTitle());
        review.setReview(reviewRequest.getReview());
        review.setIsApproved(false);
        return review;
    }

    @Override
    public Review setReviewToDelete(Review review) {
        review.setIsActive(false);
        return review;
    }

    @Override
    public void executeValidationToDelete(Review review) {
        if (review.getIsApproved())
            throw new MethodNotAllowedException(
                    String.format("La review con id %s se encuentra activa.", review.getId()),
                    "reviews");
    }

    @Override
    public void executeReviewBelongsToUser(Review review, Long userId) {
        if (!review.getUser().getId().equals(userId))
            throw new ForbiddenException(
                    String.format("El usuario no tiene una review con id %s.", review.getId()),
                    "reviews");
    }
}
