package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

public class MaxRoomCapacitySetEvent extends Event {

    private RoomNr _roomNr;
    private int _maxCapacity;

    public RoomNr roomNr() {
        return _roomNr;
    }

    public int maxCapacity() {
        return _maxCapacity;
    }

    public MaxRoomCapacitySetEvent(RoomNr roomNr, int maxCapacity) {
        _roomNr = roomNr;
        _maxCapacity = maxCapacity;
    }
}
