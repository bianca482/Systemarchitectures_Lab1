package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.BookingNr;

public class CancelRoomCommand {
    private RoomNr roomNr;
    private BookingNr bookingNr;

    public CancelRoomCommand(RoomNr roomNr, BookingNr bookingNr) {
        this.roomNr = roomNr;
        this.bookingNr = bookingNr;
    }

    public BookingNr getBookingNr() {
        return bookingNr;
    }

    public CancelRoomCommand(RoomNr roomNr) {
        this.roomNr = roomNr;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }
}
