package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomCategory;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Room {

    private RoomNr _roomNr;
    private RoomCategory _category;
    private List<Event> _roomEventLog = new LinkedList<>();

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

    public List<Event> getEvents(){
        return _roomEventLog;
    }

    public void addEvent(Event event){
        _roomEventLog.add(event);
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
