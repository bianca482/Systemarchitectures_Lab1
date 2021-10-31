package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model;

import java.util.Objects;

public class Room {
    private RoomNr _roomNr;
    private int _maxCapacity;

    public Room(RoomNr roomNr, int maxCapacity) {
        _roomNr = roomNr;
        _maxCapacity = maxCapacity;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public int maxCapacity() {
        return _maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return _maxCapacity == room._maxCapacity && Objects.equals(_roomNr, room._roomNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_roomNr, _maxCapacity);
    }
}
