package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.ReservationNr;
import java.time.LocalDateTime;

public class RoomBookedEvent extends Event {
   private RoomNr _roomNr;
   private ReservationNr _reservationNr;
   private LocalDateTime _checkInDate;
   private LocalDateTime _checkOutDate;
   private GuestId _guestId;

    public RoomBookedEvent(RoomNr roomNr, ReservationNr reservationNr, LocalDateTime checkInTime, LocalDateTime checkOutTime, GuestId guestId) {
        _roomNr = roomNr;
        _reservationNr = reservationNr;
        _checkInDate = checkInTime;
        _checkOutDate = checkOutTime;
        _guestId = guestId;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }

    public LocalDateTime checkInDate() {
        return _checkInDate;
    }

    public LocalDateTime checkOutDate() {
        return _checkOutDate;
    }

    public GuestId guestId() {
        return _guestId;
    }

    @Override
    public String toString() {
        return "RoomBookedEvent: " +
                "roomNr=" + _roomNr.number() +
                ", reservationNr=" + _reservationNr +
                ", checkInDate=" + _checkInDate +
                ", checkOutDate=" + _checkOutDate+
                ", guestId=" + _guestId;
    }
}
