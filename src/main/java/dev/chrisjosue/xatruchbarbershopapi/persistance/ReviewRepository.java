package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndIsActiveIsTrue(Long id);
    List<Review> findAllByIsActiveIsTrue();
    List<Review> findAllByIsApprovedIsTrue();
    List<Review> findAllByUserIdAndIsActiveIsTrue(Long userId);
}
