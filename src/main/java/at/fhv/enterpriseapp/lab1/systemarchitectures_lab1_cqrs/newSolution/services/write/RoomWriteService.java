package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.write;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.implementation.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.MaxRoomCapacitySetEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands.RoomMaxCapacityCommand;

public class RoomWriteService {

    private RoomRepository _roomRepository;
    private RandomIDCreator _idCreator = new RandomIDCreatorImpl();

    public RoomWriteService(RoomRepository roomRepository) {
        _roomRepository = roomRepository;
    }

    public void applyRoomMaxCapacityCommand(RoomMaxCapacityCommand roomMaxCapacityCommand) {
        Room room = _roomRepository.get(roomMaxCapacityCommand.roomNr());
        MaxRoomCapacitySetEvent roomCapacitySetEvent = new MaxRoomCapacitySetEvent(roomMaxCapacityCommand.roomNr(), roomMaxCapacityCommand.maxCapacity());
        room.addRoomEvent(roomCapacitySetEvent);
        _roomRepository.save(room);
    }
}
