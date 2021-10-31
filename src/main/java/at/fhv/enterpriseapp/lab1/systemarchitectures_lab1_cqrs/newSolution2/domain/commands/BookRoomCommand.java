package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.GuestId;

import java.time.LocalDateTime;

// BookRoom (mit zumindest Zeitraum, Zimmernummer, Personendaten)
public class BookRoomCommand {
    private LocalDateTime _checkInDate;
    private LocalDateTime _checkOutDate;
    private RoomNr _roomNr;
    private GuestId _guestId;

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

    public GuestId guestId() { return _guestId; }
}
