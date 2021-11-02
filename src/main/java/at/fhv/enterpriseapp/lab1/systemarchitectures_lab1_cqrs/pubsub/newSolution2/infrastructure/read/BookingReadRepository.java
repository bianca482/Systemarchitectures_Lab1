package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.PubSubService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.Subscriber;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.RoomNr;

import java.util.*;
import java.util.stream.Collectors;

public class BookingReadRepository extends Subscriber {
    private Map<Integer, List<Booking>> _bookings;

    public BookingReadRepository() {
        _bookings = new HashMap<>();
    }

    public BookingReadRepository(PubSubService pubSubService) {
        this();
        subscribe(pubSubService);
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

    @Override
    public void subscribe(PubSubService pubSubService) {
        pubSubService.addSubscriber(this);
    }
}
