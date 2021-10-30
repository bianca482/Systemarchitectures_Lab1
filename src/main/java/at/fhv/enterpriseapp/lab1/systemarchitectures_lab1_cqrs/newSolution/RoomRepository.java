package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Room;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RoomRepository {

    // In-Memory "Datenbank" aufbauen
    public RoomRepository(List<RoomNr> rooms) {
        for (RoomNr room : rooms) {
            _storageMap.put(room, new LinkedList<>());
        }
    }

    protected HashMap<RoomNr, List<Event>> _storageMap = new HashMap<RoomNr, List<Event>>();

    protected List<Projection> projections = new LinkedList<>();

    // Welche Events sind neu? Publish neue Events an Projections
    public void save(Room room) {
        List<Event> oldEventList;
        if (_storageMap.containsKey(room.roomNr())) {
            oldEventList = _storageMap.get(room.roomNr());
        } else {
            oldEventList = new LinkedList<>();
        }

        List<Event> newEventList = room.getEvents();

        for (int i = oldEventList.size(); i < newEventList.size(); i++) {
            publish(newEventList.get(i));
        }
        _storageMap.put(room.roomNr(), newEventList);

    }

    public Room get(RoomNr roomNr) {
        List<Event> eventLog;
        if (_storageMap.containsKey(roomNr)) {
            eventLog = _storageMap.get(roomNr);
        } else {
            eventLog = new LinkedList<>();
        }

        Room room = new Room(roomNr, null);
        for (Event event : eventLog) {
            room.addEvent(event);
        }
        return room;

    }

    private void publish(Event event) {
        for (Projection projection : projections) {
            projection.receiveEvent(event);
        }
    }

    public void subscribe(Projection projection) {
        projections.add(projection);
    }
}
