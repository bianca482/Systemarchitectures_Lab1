package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Model;

import java.util.Objects;

public class Room {
    private RoomNr _roomNr;
    private RoomCategory _category;

    public Room(RoomNr roomNr, RoomCategory category) {
        _roomNr = roomNr;
        _category = category;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public RoomCategory category() {
        return _category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(_roomNr, room._roomNr) && _category == room._category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_roomNr, _category);
    }
}
