package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;

public class RoomCancelledEvent extends Event {
    private ReservationNr _reservationNr;

    public RoomCancelledEvent(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }

    public void setReservationNr(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }

    @Override
    public String toString() {
        return "RoomCancelledEvent: " +
                "reservationNr=" + _reservationNr;
    }
}
