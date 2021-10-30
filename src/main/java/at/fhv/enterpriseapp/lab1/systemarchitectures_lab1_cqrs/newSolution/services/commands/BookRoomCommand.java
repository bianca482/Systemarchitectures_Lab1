package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

import java.time.LocalDateTime;

public class BookRoomCommand {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private RoomNr roomNr;

    public BookRoomCommand(LocalDateTime startDate, LocalDateTime endDate, RoomNr roomNr) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomNr = roomNr;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }
}
