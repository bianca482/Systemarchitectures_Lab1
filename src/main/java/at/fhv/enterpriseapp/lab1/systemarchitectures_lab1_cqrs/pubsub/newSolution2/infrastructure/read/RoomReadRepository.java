package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.RoomNr;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        for(int i = 0; i <= 30; i++) {
            rooms.add(new Room(new RoomNr(i), random.nextInt(4)));
        }
        return rooms;
    }
}
