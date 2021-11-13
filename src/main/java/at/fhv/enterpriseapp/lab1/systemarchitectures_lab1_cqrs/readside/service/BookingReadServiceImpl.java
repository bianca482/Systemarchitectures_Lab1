package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class BookingReadServiceImpl implements BookingReadService {

    @Autowired
    private BookingReadRepository readRepository;

    public BookingReadServiceImpl() {

    }

    // Prüft, welche Buchungen im gewünschten Zeitraum verfügbar sind
    @Override
    public List<Booking> handleQuery(GetBookingsInTimeRangeQuery getBookingsInTimeRangeQuery) {
        List<Booking> bookings = readRepository.getAllBookings();
        List<Booking> result = new LinkedList<>();

        for (Booking booking : bookings) {
            if (!(booking.getCheckOutDate().isBefore(getBookingsInTimeRangeQuery.getCheckInDate()) || booking.getCheckInDate().isAfter(getBookingsInTimeRangeQuery.getCheckOutDate()))) {
                result.add(booking);
            }
        }
        return result;
    }

    @Override
    public Optional<Booking> handleQuery(GetBookingQuery getBookingQuery) {
        List<Booking> bookingsByRoomNr = readRepository.getAllBookings(getBookingQuery.getRoomNr());
        if (bookingsByRoomNr != null) {
            for (Booking b : bookingsByRoomNr) {
                if (b.getGuestId().equals(getBookingQuery.getGuestId())) {
                    return Optional.of(b);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Booking> getAllBookings() {
        return readRepository.getAllBookings();
    }
}
