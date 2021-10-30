package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

import java.time.LocalDateTime;

public class RoomBooking {
    RoomNr roomNr;
    BookingNr bookingNr;
    LocalDateTime StartTime;
    LocalDateTime EndTime;

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public BookingNr getBookingNr() {
        return bookingNr;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public RoomBooking(RoomNr roomNr, BookingNr bookingNr, LocalDateTime startTime, LocalDateTime endTime) {
        this.roomNr = roomNr;
        this.bookingNr = bookingNr;
        StartTime = startTime;
        EndTime = endTime;
    }
}
