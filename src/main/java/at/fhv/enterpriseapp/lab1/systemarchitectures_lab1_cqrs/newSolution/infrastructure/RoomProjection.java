package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.MaxRoomCapacitySetEvent;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class RoomProjection implements Projection {

    public HashMap<RoomNr, Room> _roomInfoMap = new HashMap<>();

    @Override
    public void receiveEvent(Event event) {
        if (event instanceof MaxRoomCapacitySetEvent) {
            MaxRoomCapacitySetEvent maxRoomCapacitySetEvent = (MaxRoomCapacitySetEvent) event;
            _roomInfoMap.put(maxRoomCapacitySetEvent.roomNr(), new Room(maxRoomCapacitySetEvent.roomNr(), maxRoomCapacitySetEvent.maxCapacity()));
        }
    }

    public List<Room> getAllRooms() {
        return new LinkedList<>(_roomInfoMap.values());
    }
}
