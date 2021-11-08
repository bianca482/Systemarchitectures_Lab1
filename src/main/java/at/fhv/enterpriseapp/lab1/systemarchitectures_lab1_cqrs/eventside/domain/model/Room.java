package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model;

import java.util.Objects;

public class Room {
    private RoomNr roomNr;
    private int maxCapacity;

    public Room() {

    }

    public Room(RoomNr roomNr, int maxCapacity) {
        this.roomNr = roomNr;
        this.maxCapacity = maxCapacity;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setRoomNr(RoomNr roomNr) {
        this.roomNr = roomNr;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return maxCapacity == room.maxCapacity && Objects.equals(roomNr, room.roomNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNr, maxCapacity);
    }

    @Override
    public String toString() {
        return "Room{" +
                "_roomNr=" + roomNr +
                ", _maxCapacity=" + maxCapacity +
                '}';
    }
}
