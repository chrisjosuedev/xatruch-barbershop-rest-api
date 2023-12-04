package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.app.ReviewService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.ResourceNotFoundException;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import dev.chrisjosue.xatruchbarbershopapi.persistance.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository
                .findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Review con id %s no encontrada.", id), "id"));
    }

    @Override
    public void remove(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll(Boolean approved) {
        if (approved != null && approved) return reviewRepository.findAllByIsApprovedIsTrue();
        return reviewRepository.findAllByIsActiveIsTrue();
    }

    @Override
    public List<Review> findAllById(List<Long> ids) {
        return reviewRepository.findAllById(ids);
    }

    @Override
    public void approveReviews(List<Review> reviews) {
        reviewRepository.saveAll(reviews);
    }

    @Override
    public List<Review> findAllApprovedReviews() {
        return reviewRepository.findAllByIsApprovedIsTrue();
    }
}
