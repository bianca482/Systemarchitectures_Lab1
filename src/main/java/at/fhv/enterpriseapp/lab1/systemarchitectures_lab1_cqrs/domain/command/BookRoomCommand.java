package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.command;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

import java.time.LocalDate;

public class BookRoomCommand {
    private LocalDate _checkInDate;
    private LocalDate _checkOutDate;
    private RoomNr _roomNr;
    private GuestId _guestId;
    private int _guestCount;
    private String _guestName;

    public BookRoomCommand(LocalDate checkInDate, LocalDate checkOutDate, RoomNr roomNr, GuestId guestId, int guestCount, String guestName) {
        _checkInDate = checkInDate;
        _checkOutDate = checkOutDate;
        _roomNr = roomNr;
        _guestId = guestId;
        _guestCount = guestCount;
        _guestName = guestName;
    }

    public LocalDate checkInDate() {
        return _checkInDate;
    }

    public LocalDate checkOutDate() {
        return _checkOutDate;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public GuestId guestId() {
        return _guestId;
    }

    public int guestCount() {
        return _guestCount;
    }

    public String guestName() {
        return _guestName;
    }
}
