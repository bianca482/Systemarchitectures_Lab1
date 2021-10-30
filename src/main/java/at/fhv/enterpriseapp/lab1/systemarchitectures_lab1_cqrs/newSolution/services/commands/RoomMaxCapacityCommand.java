package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

public class RoomMaxCapacityCommand {
    RoomNr roomNr;

    int maxCapacity;

    public RoomMaxCapacityCommand(RoomNr roomNr, int maxCapacity) {
        this.roomNr = roomNr;
        this.maxCapacity = maxCapacity;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
