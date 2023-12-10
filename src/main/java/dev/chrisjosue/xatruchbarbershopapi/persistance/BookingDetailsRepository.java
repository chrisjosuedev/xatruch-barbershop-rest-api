package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetail, Long> {}
