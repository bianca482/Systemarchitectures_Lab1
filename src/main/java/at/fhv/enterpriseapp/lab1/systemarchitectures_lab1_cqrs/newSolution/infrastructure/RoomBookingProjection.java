package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.RoomBooking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomCancelledEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RoomBookingProjection implements Projection {
    // Um anhand der ReservationNr alle freien Räume an einem gewissen Datum herauszufinden
    private HashMap<ReservationNr, RoomBooking> _bookingsMap = new HashMap<>();
    // Um anhand der RoomNr alle dazugehörigen Buchungen zu finden
    private HashMap<RoomNr, List<RoomBooking>> _roomBookingMap = new HashMap<>();

    @Override
    public void receiveEvent(Event event) {
        if (event instanceof RoomBookedEvent) {
            RoomBookedEvent roomBookedEvent = (RoomBookedEvent) event;
            if (!_bookingsMap.containsKey(roomBookedEvent.reservationNr())) {
                RoomBooking roomBooking = new RoomBooking(roomBookedEvent.roomNr(), roomBookedEvent.reservationNr(), roomBookedEvent.checkInTime(), roomBookedEvent.checkOutTime());
                _bookingsMap.put(roomBookedEvent.reservationNr(), roomBooking);

                if (!_roomBookingMap.containsKey(roomBookedEvent.roomNr())) {
                    _roomBookingMap.put(roomBookedEvent.roomNr(), new LinkedList<>());
                }
                List<RoomBooking> roomBookings = _roomBookingMap.get(roomBookedEvent.roomNr());
                roomBookings.add(roomBooking);
            }
        } else if (event instanceof RoomCancelledEvent) {
            RoomCancelledEvent roomCancelledEvent = (RoomCancelledEvent) event;
            if (_bookingsMap.containsKey(roomCancelledEvent.reservationNr())) {
                RoomBooking roomBooking = _bookingsMap.remove(roomCancelledEvent.reservationNr());

                List<RoomBooking> roomBookings = _roomBookingMap.get(roomCancelledEvent.roomNr());
                roomBookings.remove(roomBooking);
            }
        }
    }

    public List<RoomBooking> getAllBookings() {
        return new LinkedList<>(_bookingsMap.values());
    }

    public List<RoomBooking> getAllBookings(RoomNr roomNr) {
        return _roomBookingMap.get(roomNr);
    }
}
