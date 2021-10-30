package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.BookingNr;

public class RoomCancelledEvent implements Event {

    public RoomNr roomNr;

    public RoomCancelledEvent(RoomNr roomNr, BookingNr bookingNr) {
        this.roomNr = roomNr;
        this.bookingNr = bookingNr;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public BookingNr getBookingNr() {
        return bookingNr;
    }

    public BookingNr bookingNr;
}
