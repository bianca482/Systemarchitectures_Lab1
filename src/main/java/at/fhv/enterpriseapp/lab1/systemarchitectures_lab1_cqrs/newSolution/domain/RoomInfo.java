package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;

public class RoomInfo {
   private RoomNr roomNr;
   private int maxCapacity;


    public RoomInfo(RoomNr roomNr, int maxCapacity) {
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
