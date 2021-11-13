package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoomReadRepository {
    private List<Room> rooms;

    public RoomReadRepository(List<Room> rooms) {
        this.rooms = rooms;
    }

    public RoomReadRepository() {
        rooms = createRooms();
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> createRooms() {
        Room room1 = new Room(new RoomNr(1), 2);
        Room room2 = new Room(new RoomNr(2), 2);
        Room room3 = new Room(new RoomNr(3), 1);
        Room room4 = new Room(new RoomNr(4), 1);
        Room room5 = new Room(new RoomNr(5), 2);
        Room room6 = new Room(new RoomNr(6), 3);
        Room room7 = new Room(new RoomNr(7), 3);
        Room room8 = new Room(new RoomNr(8), 4);
        Room room9 = new Room(new RoomNr(9), 1);
        Room room10 = new Room(new RoomNr(10), 2);
        Room room11 = new Room(new RoomNr(11), 2);
        Room room12 = new Room(new RoomNr(12), 2);
        Room room13 = new Room(new RoomNr(13), 2);
        Room room14 = new Room(new RoomNr(14), 2);
        Room room15 = new Room(new RoomNr(15), 2);
        Room room16 = new Room(new RoomNr(16), 2);
        Room room17 = new Room(new RoomNr(17), 2);
        Room room18 = new Room(new RoomNr(18), 1);
        Room room19 = new Room(new RoomNr(19), 2);

        List<Room> rooms = new LinkedList<>();
        rooms.add(0, room1);
        rooms.add(1, room2);
        rooms.add(2, room3);
        rooms.add(3, room4);
        rooms.add(4, room5);
        rooms.add(5, room6);
        rooms.add(6, room7);
        rooms.add(7, room8);
        rooms.add(8, room9);
        rooms.add(9, room10);
        rooms.add(10, room11);
        rooms.add(11, room12);
        rooms.add(12, room13);
        rooms.add(13, room14);
        rooms.add(14, room15);
        rooms.add(15, room16);
        rooms.add(16, room17);
        rooms.add(17, room18);
        rooms.add(18, room19);

        return rooms;
    }
}
