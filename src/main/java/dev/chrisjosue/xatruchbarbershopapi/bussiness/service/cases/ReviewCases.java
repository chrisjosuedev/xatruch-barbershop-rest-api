package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.cases;

import dev.chrisjosue.xatruchbarbershopapi.domain.dto.request.ReviewRequest;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;

import java.util.List;

public interface ReviewCases {
    Review setReviewToSave(Review review, User user);
    Review setReviewToUpdate(Review review, ReviewRequest reviewRequest);
    Review setReviewToDelete(Review review);
    List<Review> setReviewsToApprove(List<Review> reviewsPending, List<Review> reviewsCurrent);
    void executeValidationToDelete(Review review);
    void executeReviewBelongsToUser(Review review, Long userId);
}
