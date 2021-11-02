package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.Event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EventRepository {

    private HashMap<RoomNr, List<Event>> _eventStorageMap = new HashMap<>();
    private List<Projection> _projections = new LinkedList<>();

    // In-Memory "Datenbank" aufbauen
    public EventRepository(List<RoomNr> rooms) {
        for (RoomNr room : rooms) {
            _eventStorageMap.put(room, new LinkedList<>());
        }
    }

    public void save(Room room) {
        List<Event> oldEventList;
        if (_eventStorageMap.containsKey(room.roomNr())) {
            oldEventList = _eventStorageMap.get(room.roomNr());
        } else {
            oldEventList = new LinkedList<>();
        }

        // Welche Events sind neu? Publish neue Events an Projections
        List<Event> newEventList = room.roomEvents();

        for (int i = oldEventList.size(); i < newEventList.size(); i++) {
            publish(newEventList.get(i));
        }
        _eventStorageMap.put(room.roomNr(), newEventList);
    }

    public Room get(RoomNr roomNr) {
        List<Event> eventLog;
        if (_eventStorageMap.containsKey(roomNr)) {
            eventLog = _eventStorageMap.get(roomNr);
        } else {
            eventLog = new LinkedList<>();
        }

        Room room = new Room(roomNr, 0);
        for (Event event : eventLog) {
            room.addRoomEvent(event);
        }
        return room;
    }

    private void publish(Event event) {
        for (Projection projection : _projections) {
            projection.receiveEvent(event);
        }
    }

    public void subscribe(Projection projection) {
        _projections.add(projection);
    }
}
