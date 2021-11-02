package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomCancelledEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BookingProjection implements Projection {
    // Um anhand der ReservationNr alle freien Räume an einem gewissen Datum herauszufinden
    private HashMap<ReservationNr, Booking> _bookingsMap = new HashMap<>();
    // Um anhand der RoomNr alle dazugehörigen Buchungen zu finden
    private HashMap<RoomNr, List<Booking>> _roomBookingMap = new HashMap<>();

    @Override
    public void receiveEvent(Event event) {
        if (event instanceof RoomBookedEvent) {
            RoomBookedEvent roomBookedEvent = (RoomBookedEvent) event;
            if (!_bookingsMap.containsKey(roomBookedEvent.reservationNr())) {
                Booking booking = new Booking(roomBookedEvent.roomNr(), roomBookedEvent.reservationNr(), roomBookedEvent.checkInTime(), roomBookedEvent.checkOutTime());
                _bookingsMap.put(roomBookedEvent.reservationNr(), booking);

                if (!_roomBookingMap.containsKey(roomBookedEvent.roomNr())) {
                    _roomBookingMap.put(roomBookedEvent.roomNr(), new LinkedList<>());
                }
                List<Booking> bookings = _roomBookingMap.get(roomBookedEvent.roomNr());
                bookings.add(booking);
            }
        } else if (event instanceof RoomCancelledEvent) {
            RoomCancelledEvent roomCancelledEvent = (RoomCancelledEvent) event;
            if (_bookingsMap.containsKey(roomCancelledEvent.reservationNr())) {
                Booking booking = _bookingsMap.remove(roomCancelledEvent.reservationNr());

                List<Booking> bookings = _roomBookingMap.get(roomCancelledEvent.roomNr());
                bookings.remove(booking);
            }
        }
    }

    public List<Booking> getAllBookings() {
        return new LinkedList<>(_bookingsMap.values());
    }

    public List<Booking> getAllBookings(RoomNr roomNr) {
        return _roomBookingMap.get(roomNr);
    }
}
