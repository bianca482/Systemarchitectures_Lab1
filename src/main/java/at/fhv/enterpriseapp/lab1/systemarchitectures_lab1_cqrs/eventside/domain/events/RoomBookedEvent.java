package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import java.time.LocalDateTime;

public class RoomBookedEvent extends Event {
   private RoomNr _roomNr;
   private ReservationNr _reservationNr;
   private LocalDateTime _checkInDate;
   private LocalDateTime _checkOutDate;
   private GuestId _guestId;

   public RoomBookedEvent() {

   }

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

    public void setRoomNr(RoomNr _roomNr) {
        this._roomNr = _roomNr;
    }

    public void setReservationNr(ReservationNr reservationNr) {
        _reservationNr = reservationNr;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        _checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        _checkOutDate = checkOutDate;
    }

    public void setGuestId(GuestId guestId) {
        _guestId = guestId;
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
