package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.ReservationNr;

public class RoomCancelledEvent extends Event {

    private RoomNr _roomNr;
    private ReservationNr _reservationNr;

    public RoomCancelledEvent(RoomNr roomNr, ReservationNr reservationNr) {
        _roomNr = roomNr;
        _reservationNr = reservationNr;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }
}
