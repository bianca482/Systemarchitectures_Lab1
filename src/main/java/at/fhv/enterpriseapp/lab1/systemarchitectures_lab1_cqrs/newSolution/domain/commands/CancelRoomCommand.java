package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.ReservationNr;

public class CancelRoomCommand {
    // TODO RoomNr wegbringen
    private RoomNr _roomNr;
    private ReservationNr _reservationNr;

    public CancelRoomCommand(RoomNr roomNr, ReservationNr reservationNr) {
        _roomNr = roomNr;
        _reservationNr = reservationNr;
    }

    public ReservationNr ReservationNr() {
        return _reservationNr;
    }

    public CancelRoomCommand(RoomNr roomNr) {
        _roomNr = roomNr;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }
}
