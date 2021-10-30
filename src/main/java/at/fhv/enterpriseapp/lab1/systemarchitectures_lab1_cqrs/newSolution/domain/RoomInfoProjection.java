package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Projection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.MaxRoomCapacitySetEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RoomInfoProjection implements Projection {

    public HashMap<RoomNr, RoomInfo> roomInfoMap = new HashMap<>();

    @Override
    public void receiveEvent(Event event) {
        if(event instanceof MaxRoomCapacitySetEvent){
            MaxRoomCapacitySetEvent maxRoomCapacitySetEvent =  (MaxRoomCapacitySetEvent)event;
            roomInfoMap.put(maxRoomCapacitySetEvent.getRoomNr(), new RoomInfo(maxRoomCapacitySetEvent.getRoomNr(), maxRoomCapacitySetEvent.getMaxCapacity()));
        }

    }

    public List<RoomInfo> getAllRooms(){
        return new LinkedList<>(roomInfoMap.values());
    }


}
