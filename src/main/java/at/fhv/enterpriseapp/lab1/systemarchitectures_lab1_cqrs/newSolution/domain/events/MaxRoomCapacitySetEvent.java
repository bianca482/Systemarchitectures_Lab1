package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Event;

public class MaxRoomCapacitySetEvent implements Event {

    RoomNr roomNr;
    int maxCapacity;

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public MaxRoomCapacitySetEvent(RoomNr roomNr, int maxCapacity) {
        this.roomNr = roomNr;
        this.maxCapacity = maxCapacity;
    }
}
