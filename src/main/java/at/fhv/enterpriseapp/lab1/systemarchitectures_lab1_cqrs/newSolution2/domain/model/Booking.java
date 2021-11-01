package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model;

import java.time.LocalDateTime;

public class Booking {
    private RoomNr _roomNr;
    private ReservationNr _reservationNr;
    private LocalDateTime _checkInDate;
    private LocalDateTime _checkOutDate;
    private GuestId _guestId;

    public Booking(RoomNr roomNr, ReservationNr reservationNr, LocalDateTime checkInTime, LocalDateTime checkOutTime, GuestId guestId) {
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
        return "Booking{" +
                "_roomNr=" + _roomNr +
                ", _reservationNr=" + _reservationNr +
                ", _checkInDate=" + _checkInDate +
                ", _checkOutDate=" + _checkOutDate +
                ", _guestId=" + _guestId +
                '}';
    }
}
