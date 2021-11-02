package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Room {

    private RoomNr _roomNr;
    private int _maxCapacity;
    private List<Event> _roomEventLog = new LinkedList<>();

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

    public List<Event> roomEvents(){
        return _roomEventLog;
    }

    public void addRoomEvent(Event event){
        _roomEventLog.add(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return _maxCapacity == room._maxCapacity && Objects.equals(_roomNr, room._roomNr) && Objects.equals(_roomEventLog, room._roomEventLog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_roomNr, _maxCapacity, _roomEventLog);
    }
}
