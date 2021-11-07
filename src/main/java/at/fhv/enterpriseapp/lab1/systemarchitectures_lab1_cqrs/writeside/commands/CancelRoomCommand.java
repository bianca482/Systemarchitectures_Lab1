package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;

// CancelBooking (mit Reservierungsnummer)
public class CancelRoomCommand {
    private ReservationNr _reservationNr;

    public CancelRoomCommand(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }

    public void setReservationNr(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }
}