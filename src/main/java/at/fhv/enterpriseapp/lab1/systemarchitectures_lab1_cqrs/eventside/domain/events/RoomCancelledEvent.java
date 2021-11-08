package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;

public class RoomCancelledEvent extends Event {
    private ReservationNr reservationNr;

    public RoomCancelledEvent() {

    }

    public RoomCancelledEvent(ReservationNr reservationNr) {
        this.reservationNr = reservationNr;
    }

    public ReservationNr getReservationNr() {
        return reservationNr;
    }

    public void setReservationNr(ReservationNr reservationNr) {
        this.reservationNr = reservationNr;
    }

    @Override
    public String toString() {
        return "RoomCancelledEvent: " +
                "reservationNr=" + reservationNr;
    }
}
