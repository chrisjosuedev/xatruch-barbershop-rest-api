package dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.cases.ReviewCases;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ForbiddenException;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.MethodNotAllowedException;
import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

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
    public List<Review> setReviewsToApprove(List<Review> reviewsPending, List<Review> reviewsCurrent) {
        // Get remaining Reviews to Change Approve to False;
        reviewsCurrent.removeAll(reviewsPending);

        reviewsCurrent.forEach(review -> review.setIsApproved(false));
        reviewsPending.forEach(review -> review.setIsApproved(true));

        return Stream.concat(reviewsPending.stream(), reviewsCurrent.stream()).toList();
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
