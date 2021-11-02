package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure.Projection;

import java.util.*;
import java.util.stream.Collectors;

public class BookingReadRepository implements Projection {
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

    @Override
    public void receiveEvent(Event event) {
            // RoomBookedEvent
        if (event instanceof RoomBookedEvent) {
            RoomBookedEvent roomBookedEvent = (RoomBookedEvent) event;
            if (!_bookings.containsKey(roomBookedEvent.roomNr())) {

                _bookings.put(roomBookedEvent.roomNr().number(), new LinkedList<>());
            }
            List<Booking> bookings = _bookings.get(roomBookedEvent.roomNr().number());
            Booking booking = new Booking(roomBookedEvent.roomNr(), roomBookedEvent.reservationNr(), roomBookedEvent.checkInDate(), roomBookedEvent.checkOutDate(), roomBookedEvent.guestId());
            bookings.add(booking);

            // RoomCanceledEvent
        } else if (event instanceof RoomCancelledEvent) {
            RoomCancelledEvent roomCancelledEvent = (RoomCancelledEvent) event;
            Booking bookingToCancel = null;

            for (List<Booking> list : _bookings.values()) {
                for (Booking value : list) {
                    if (value.reservationNr().equals(roomCancelledEvent.reservationNr())) {
                        bookingToCancel = value;
                    }
                }
                if(bookingToCancel != null){
                    break;
                }
            }
            if (bookingToCancel != null) {
                List<Booking> bookings = _bookings.get(bookingToCancel.roomNr().number());
                bookings.remove(bookingToCancel);
            }
        }
    }
}
