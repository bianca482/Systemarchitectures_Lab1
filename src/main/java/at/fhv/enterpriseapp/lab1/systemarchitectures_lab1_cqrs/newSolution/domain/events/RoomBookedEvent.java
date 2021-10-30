package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.BookingNr;

import java.time.LocalDateTime;

public class RoomBookedEvent implements Event {

   public RoomNr roomNr;
   public BookingNr bookingNr;
   public LocalDateTime StartTime;
   public LocalDateTime EndTime;

    public RoomBookedEvent(RoomNr roomNr, BookingNr bookingNr, LocalDateTime startTime, LocalDateTime endTime) {
        this.roomNr = roomNr;
        this.bookingNr = bookingNr;
        StartTime = startTime;
        EndTime = endTime;
    }
}
