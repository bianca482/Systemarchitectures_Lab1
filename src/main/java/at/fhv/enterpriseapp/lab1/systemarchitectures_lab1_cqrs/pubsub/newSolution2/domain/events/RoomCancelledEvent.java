package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.ReservationNr;

public class RoomCancelledEvent extends Event {
    private ReservationNr _reservationNr;

    public RoomCancelledEvent(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }
}
