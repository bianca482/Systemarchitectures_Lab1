package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.application.services.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.queries.AllBookingsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure.read.BookingReadRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BookingReadService {
    private BookingReadRepository _readRepository;

    public BookingReadService(BookingReadRepository readRepository) {
        _readRepository = readRepository;
    }

    // Prüft, welche Buchungen im gewünschten Zeitraum verfügbar sind
    public List<Booking> handleQuery(AllBookingsQuery allBookingsQuery) {
        List<Booking> bookings = _readRepository.getAllBookings();
        List<Booking> result = new LinkedList<>();

        for (Booking booking : bookings) {
            /*if (booking.checkInDate().isBefore(allBookingsQuery.checkInDate()) && booking.checkOutDate().isAfter(allBookingsQuery.checkOutDate())) {
                result.add(booking);
            } else if (booking.checkInDate().isAfter(allBookingsQuery.checkInDate()) && booking.checkOutDate().isBefore(allBookingsQuery.checkOutDate())) {
                result.add(booking);
            } else if (booking.checkInDate().isBefore(allBookingsQuery.checkInDate()) && booking.checkOutDate().isAfter(allBookingsQuery.checkOutDate())) {
                result.add(booking);
            } else if (booking.checkInDate().isBefore(allBookingsQuery.checkOutDate()) && booking.checkOutDate().isAfter(allBookingsQuery.checkOutDate())) {
                result.add(booking);
            }
            nur 2. Abfrage gültig
            wenn Buchungen nur checkOut oder checkIn Datum im gewählten Zeitraum haben dann folgende Möglichkeit:
            if (!(booking.checkOutDate().isBefore(allBookingsQuery.checkInDate()) || booking.checkInDate().isAfter(allBookingsQuery.checkOutDate()))) {
                result.add(booking);
            }
             */
            if (booking.checkInDate().isAfter(allBookingsQuery.checkInDate()) && booking.checkOutDate().isBefore(allBookingsQuery.checkOutDate())) {
                result.add(booking);
            }
        }
        return result;
    }

    public Optional<Booking> handleQuery(GetBookingQuery getBookingQuery) {
        return _readRepository.getBookingByGuestId(getBookingQuery.roomNr(), getBookingQuery.guestId());
    }
}
