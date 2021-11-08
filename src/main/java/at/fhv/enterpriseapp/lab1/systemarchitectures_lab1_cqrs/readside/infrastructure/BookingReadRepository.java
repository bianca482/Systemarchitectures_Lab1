package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.Projection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookingReadRepository implements Projection {
    private Map<Integer, List<Booking>> bookings;

    public BookingReadRepository() {
        bookings = new HashMap<>();
    }

    public List<Booking> getAllBookings() {
        //List<List<Booking>> in eine einzelne List konvertieren
        return bookings.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<Booking> getAllBookings(RoomNr roomNr) {
        if (bookings.containsKey(roomNr.getNumber())) {
            return bookings.get(roomNr.getNumber());
        }
        return Collections.emptyList();
    }

    @Override
    public void receiveEvent(Event event) {
        // RoomBookedEvent
        if (event instanceof RoomBookedEvent) {
            RoomBookedEvent roomBookedEvent = (RoomBookedEvent) event;
            if (!bookings.containsKey(roomBookedEvent.getRoomNr().getNumber())) {
                bookings.put(roomBookedEvent.getRoomNr().getNumber(), new LinkedList<>());
            }
            List<Booking> bookings = this.bookings.get(roomBookedEvent.getRoomNr().getNumber());
            Booking booking = new Booking(roomBookedEvent.getRoomNr(), roomBookedEvent.getReservationNr(), roomBookedEvent.getCheckInDate(), roomBookedEvent.getCheckOutDate(), roomBookedEvent.getGuestId());
            bookings.add(booking);

        // RoomCanceledEvent
        } else if (event instanceof RoomCancelledEvent) {
            RoomCancelledEvent roomCancelledEvent = (RoomCancelledEvent) event;
            Booking bookingToCancel = null;

            for (List<Booking> list : bookings.values()) {
                for (Booking value : list) {
                    if (value.getReservationNr().equals(roomCancelledEvent.getReservationNr())) {
                        bookingToCancel = value;
                    }
                }
                if(bookingToCancel != null){
                    break;
                }
            }
            if (bookingToCancel != null) {
                List<Booking> bookings = this.bookings.get(bookingToCancel.getRoomNr().getNumber());
                bookings.remove(bookingToCancel);
            }
        }
    }
}
