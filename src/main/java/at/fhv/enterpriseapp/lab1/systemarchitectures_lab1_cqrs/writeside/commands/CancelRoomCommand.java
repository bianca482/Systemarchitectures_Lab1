package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;

// CancelBooking (mit Reservierungsnummer)
public class CancelRoomCommand {
    private ReservationNr reservationNr;

    public CancelRoomCommand() {

    }

    public CancelRoomCommand(ReservationNr reservationNr) {
        this.reservationNr = reservationNr;
    }

    public ReservationNr getReservationNr() {
        return reservationNr;
    }

    public void setReservationNr(ReservationNr reservationNr) {
        this.reservationNr = reservationNr;
    }
}
