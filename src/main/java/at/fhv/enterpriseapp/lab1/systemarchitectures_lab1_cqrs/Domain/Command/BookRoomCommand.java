package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Command;

import java.time.LocalDate;

public class BookRoomCommand {
    private LocalDate _checkInDate;
    private LocalDate _checkOutDate;
    private int _roomNr;
    private String _guestId;

    public BookRoomCommand(LocalDate checkInDate, LocalDate checkOutDate, int roomNr, String guestId) {
        _checkInDate = checkInDate;
        _checkOutDate = checkOutDate;
        _roomNr = roomNr;
        _guestId = guestId;
    }
}
