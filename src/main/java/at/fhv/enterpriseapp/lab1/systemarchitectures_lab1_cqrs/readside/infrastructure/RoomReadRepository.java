package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;

import java.util.*;

public class RoomReadRepository {
    private List<Room> rooms;

    public RoomReadRepository(List <Room> rooms){
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

        List <Room> rooms = new LinkedList<>();
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

        Room room30 = new Room(new RoomNr(30), 2);
        Room room31 = new Room(new RoomNr(31), 2);
        Room room32 = new Room(new RoomNr(32), 2);
        Room room33 = new Room(new RoomNr(33), 2);
        Room room34 = new Room(new RoomNr(34), 2);
        Room room35 = new Room(new RoomNr(35), 2);
        Room room36 = new Room(new RoomNr(36), 2);
        Room room37 = new Room(new RoomNr(37), 1);
        Room room38 = new Room(new RoomNr(38), 2);

        rooms.add(10, room30);
        rooms.add(11, room31);
        rooms.add(12, room32);
        rooms.add(13, room33);
        rooms.add(14, room34);
        rooms.add(15, room35);
        rooms.add(16, room36);
        rooms.add(17, room37);
        rooms.add(18, room38);

        return rooms;
    }
}
