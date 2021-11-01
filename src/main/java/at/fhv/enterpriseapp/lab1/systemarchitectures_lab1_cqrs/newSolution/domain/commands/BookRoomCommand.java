package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import java.time.LocalDateTime;

public class BookRoomCommand {
    private LocalDateTime _checkInDate;
    private LocalDateTime _checkOutDate;
    private RoomNr _roomNr;

    // TODO Personen in Buchung hinterlegen

    public BookRoomCommand(LocalDateTime checkInDate, LocalDateTime checkOutDate, RoomNr roomNr) {
        _checkInDate = checkInDate;
        _checkOutDate = checkOutDate;
        _roomNr = roomNr;
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
}
