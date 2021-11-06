package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.Projection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;

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
        if (_bookings.containsKey(roomNr.number())) {
            return _bookings.get(roomNr.number());
        }
        return Collections.emptyList();
    }

    @Override
    public void receiveEvent(Event event) {
        // RoomBookedEvent
        if (event instanceof RoomBookedEvent) {
            RoomBookedEvent roomBookedEvent = (RoomBookedEvent) event;
            if (!_bookings.containsKey(roomBookedEvent.roomNr().number())) {
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
