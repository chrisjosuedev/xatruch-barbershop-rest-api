package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
    SELECT b.bookingTime FROM Booking b
    INNER JOIN Person p on b.person.id = p.id
    WHERE p.isBarber = true AND p.id = :personId AND b.bookingDate = :bookingDate
    """)
    List<LocalTime> findAllByPersonIdAndBookingDate(Long personId, LocalDate bookingDate);

    @Query("""
    SELECT b.bookingTime FROM Booking b
    INNER JOIN Person p on b.person.id = p.id
    WHERE p.isBarber = true\s
    AND p.id = :personId AND b.bookingDate = :bookingDate AND b.bookingTime = :bookingTime
    """)
    Optional<Booking> findBookingAvailability(Long personId, LocalDate bookingDate, LocalTime bookingTime);

    List<Booking> findAllByUserId(Long userId);
}
