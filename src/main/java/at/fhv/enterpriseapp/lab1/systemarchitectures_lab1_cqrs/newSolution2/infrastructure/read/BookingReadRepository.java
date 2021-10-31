package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;

import java.util.*;
import java.util.stream.Collectors;

public class BookingReadRepository {
    private Map<Integer, List<Booking>> _bookings;

    public BookingReadRepository() {
        _bookings = new HashMap<>();
    }

    public List<Booking> getAllBookings() {
        //List<List<Booking>> in eine einzelne List konvertieren
        return _bookings.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<Booking> getAllBookings(RoomNr roomNr) {
        return _bookings.get(roomNr.number());
    }

    public Optional<Booking> getBookingByGuestId(RoomNr roomNr, GuestId guestId) {
        System.out.println(_bookings.values());
        List<Booking> bookingsByRoomNr = _bookings.get(roomNr.number());
        if (bookingsByRoomNr != null) {
            for (Booking b : bookingsByRoomNr) {
                if (b.guestId().equals(guestId)) {
                    return Optional.of(b);
                }
            }
        }
        return Optional.empty();
    }
}
