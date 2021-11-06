package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;

import java.util.*;

public class RoomReadRepository {
    private List<Room> _rooms;

    public RoomReadRepository() {
        _rooms = createRooms();
    }

    public List<Room> getAllRooms() {
        return _rooms;
    }

    public List<Room> createRooms() {
        Random random = new Random();
        List<Room> rooms = new LinkedList<>();
        for(int i = 0; i < 30; i++) {
            rooms.add(new Room(new RoomNr(i), random.nextInt(4)));
        }
        return rooms;
    }
}
