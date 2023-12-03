package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review);
    Review update(Review review);
    Review findById(Long id);
    void remove(Review review);
    List<Review> findAll(Boolean approved);
    List<Review> approveReviews(List<Long> ids);
}
