package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingCartRepository extends JpaRepository<BookingTempCart, Integer> {
    List<BookingTempCart> findAllByUserId(Long userId);
    Optional<BookingTempCart> findBookingTempCartByBarberServiceIdAndUserId(Long serviceId, Long userId);
    void deleteAllByUserId(Long userId);
}
