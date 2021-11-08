package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import java.time.LocalDateTime;

public class RoomBookedEvent extends Event {
   private RoomNr roomNr;
   private ReservationNr reservationNr;
   private LocalDateTime checkInDate;
   private LocalDateTime checkOutDate;
   private GuestId guestId;

   public RoomBookedEvent() {

   }

    public RoomBookedEvent(RoomNr roomNr, ReservationNr reservationNr, LocalDateTime checkInTime, LocalDateTime checkOutTime, GuestId guestId) {
        this.roomNr = roomNr;
        this.reservationNr = reservationNr;
        this.checkInDate = checkInTime;
        this.checkOutDate = checkOutTime;
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
        return "RoomBookedEvent: " +
                "roomNr=" + roomNr.getNumber() +
                ", reservationNr=" + reservationNr +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate+
                ", guestId=" + guestId;
    }
}
