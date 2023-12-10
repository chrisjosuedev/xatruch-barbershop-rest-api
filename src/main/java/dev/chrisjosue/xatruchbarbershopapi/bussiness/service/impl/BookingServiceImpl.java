package dev.chrisjosue.xatruchbarbershopapi.bussiness.service.impl;

import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.BookingService;
import dev.chrisjosue.xatruchbarbershopapi.bussiness.service.SettingService;
import dev.chrisjosue.xatruchbarbershopapi.common.exceptions.*;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.Booking;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingDetail;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.BookingTempCart;
import dev.chrisjosue.xatruchbarbershopapi.domain.entity.User;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingCartRepository;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingDetailsRepository;
import dev.chrisjosue.xatruchbarbershopapi.persistance.BookingRepository;
import dev.chrisjosue.xatruchbarbershopapi.persistance.SettingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingCartRepository bookingCartRepository;

    @Override
    public List<LocalTime> findAvailableTimeBarbers(Long personId, Date bookingDate, List<LocalTime> settings) {
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


}