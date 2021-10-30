package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.ReservationNr;

import java.time.LocalDateTime;

public class RoomBookedEvent extends Event {

   private RoomNr _roomNr;
   private ReservationNr _reservationNr;
   private LocalDateTime _checkInTime;
   private LocalDateTime _checkOutTime;

    public RoomBookedEvent(RoomNr roomNr, ReservationNr reservationNr, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        _roomNr = roomNr;
        _reservationNr = reservationNr;
        _checkInTime = checkInTime;
        _checkOutTime = checkOutTime;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }

    public LocalDateTime checkInTime() {
        return _checkInTime;
    }

    public LocalDateTime checkOutTime() {
        return _checkOutTime;
    }
}
