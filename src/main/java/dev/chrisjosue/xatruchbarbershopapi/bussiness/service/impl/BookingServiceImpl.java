package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.*;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingCartRepository;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingCartRepository bookingCartRepository;

    @Override
    public List<LocalTime> findAvailableTimeBarbers(Long personId, LocalDate bookingDate, List<LocalTime> settings) {
        if (bookingDate.isBefore(LocalDate.now()) || bookingDate.equals(LocalDate.now()))
            throw new ConflictException("Fecha no puede ser anterior o igual al presente.", "bookings");

        var barberBookings = bookingRepository.findAllByPersonIdAndBookingDate(personId, bookingDate);

        if (barberBookings.isEmpty()) return settings;

        settings.removeAll(barberBookings);
        return settings;
    }

    @Transactional
    @Override
    public Booking bookingSession(Booking booking, List<BookingTempCart> cart) {
        if (cart.isEmpty())
            throw new MethodNotAllowedException("El carrito está vacio, agregue al menos 1 elemento.", "cart");

        var checkAvailability = bookingRepository
                .findBookingAvailability(booking.getPerson().getId(), booking.getBookingDate(), booking.getBookingTime());

        if (checkAvailability.isPresent())
            throw new ConflictException(String.format("La fecha y hora indicada no encuentra disponible para el barbero con id %s.", booking.getPerson().getId()), "bookings");

        List<BookingDetail> allBookingsInCart = cart
                .stream()
                .map(item -> {
                    BookingDetail detail = new BookingDetail();
                    detail.setBooking(booking);
                    detail.setBarberService(item.getBarberService());
                    detail.setPrice(item.getBarberService().getPrice());
                    return detail;
                })
                .toList();

        booking.setBookingDetails(allBookingsInCart);

        // Save Order
        var bookingSaved = bookingRepository.save(booking);

        // Delete Current Cart
        bookingCartRepository.deleteAllByUserId(bookingSaved.getUser().getId());

        // Returning Booking
        return bookingSaved;
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findAllUserBookings(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

}
