package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;

import java.time.LocalDateTime;

// BookRoom (mit zumindest Zeitraum, Zimmernummer, Personendaten)
public class BookRoomCommand {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RoomNr roomNr;
    private GuestId guestId;
    // ToDo: Reicht die GuestId oder alle Attribute (Name etc.) einzeln?

    public BookRoomCommand() {

    }

    public BookRoomCommand(LocalDateTime checkInDate, LocalDateTime checkOutDate, RoomNr roomNr, GuestId guestId) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNr = roomNr;
        this.guestId = guestId;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRoomNr(RoomNr roomNr) {
        this.roomNr = roomNr;
    }

    public void setGuestId(GuestId guestId) {
        this.guestId = guestId;
    }
}
