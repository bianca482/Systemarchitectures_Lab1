package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;

import java.time.LocalDateTime;

// BookRoom (mit zumindest Zeitraum, Zimmernummer, Personendaten)
public class BookRoomCommand {
    private LocalDateTime _checkInDate;
    private LocalDateTime _checkOutDate;
    private RoomNr _roomNr;
    private GuestId _guestId;
    // ToDo: Reicht die GuestId oder alle Attribute (Name etc.) einzeln?

    public BookRoomCommand(LocalDateTime checkInDate, LocalDateTime checkOutDate, RoomNr roomNr, GuestId guestId) {
        _checkInDate = checkInDate;
        _checkOutDate = checkOutDate;
        _roomNr = roomNr;
        _guestId = guestId;
    }

    public LocalDateTime checkInDate() {
        return _checkInDate;
    }

    public LocalDateTime checkOutDate() {
        return _checkOutDate;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public GuestId guestId() {
        return _guestId;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        _checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        _checkOutDate = checkOutDate;
    }

    public void setRoomNr(RoomNr roomNr) {
        _roomNr = roomNr;
    }

    public void setGuestId(GuestId guestId) {
        _guestId = guestId;
    }
}
