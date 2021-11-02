package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;

public class RoomMaxCapacityCommand {
    private RoomNr _roomNr;
    private int _maxCapacity;

    public RoomMaxCapacityCommand(RoomNr roomNr, int maxCapacity) {
        _roomNr = roomNr;
        _maxCapacity = maxCapacity;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public int maxCapacity() {
        return _maxCapacity;
    }
}
