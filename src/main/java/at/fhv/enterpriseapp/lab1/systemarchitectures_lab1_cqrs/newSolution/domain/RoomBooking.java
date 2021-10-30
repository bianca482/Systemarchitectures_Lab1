package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

import java.time.LocalDateTime;

public class RoomBooking {
    private RoomNr _roomNr;
    private ReservationNr _reservationNr;
    private LocalDateTime _checkInTime;
    private LocalDateTime _checkOutTime;

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

    public RoomBooking(RoomNr roomNr, ReservationNr reservationNr, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        _roomNr = roomNr;
        _reservationNr = reservationNr;
        _checkInTime = checkInTime;
        _checkOutTime = checkOutTime;
    }
}
