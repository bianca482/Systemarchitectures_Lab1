package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.command;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.ReservationNr;

public class CancelBookingCommand {
    private ReservationNr _reservationNr;

    public CancelBookingCommand(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }
}
