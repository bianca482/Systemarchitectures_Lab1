package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model;

import java.time.LocalDateTime;

public class Booking {
    private RoomNr roomNr;
    private ReservationNr reservationNr;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private GuestId guestId;

    public Booking() {

    }

    public Booking(RoomNr roomNr, ReservationNr reservationNr, LocalDateTime checkInDate, LocalDateTime checkOutDate, GuestId guestId) {
        this.roomNr = roomNr;
        this.reservationNr = reservationNr;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestId = guestId;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(RoomNr roomNr) {
        this.roomNr = roomNr;
    }

    public ReservationNr getReservationNr() {
        return reservationNr;
    }

    public void setReservationNr(ReservationNr reservationNr) {
        this.reservationNr = reservationNr;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public void setGuestId(GuestId guestId) {
        this.guestId = guestId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "_roomNr=" + roomNr +
                ", _reservationNr=" + reservationNr +
                ", _checkInDate=" + checkInDate +
                ", _checkOutDate=" + checkOutDate +
                ", _guestId=" + guestId +
                '}';
    }
}
