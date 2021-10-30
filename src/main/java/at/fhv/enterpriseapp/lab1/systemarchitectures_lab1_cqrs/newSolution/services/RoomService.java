package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.implementation.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.RoomRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.BookingNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.MaxRoomCapacitySetEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.commands.RoomMaxCapacityCommand;

public class RoomService {

    private RoomRepository roomRepository;

    public RandomIDCreator idCreator = new RandomIDCreatorImpl();

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void apply(BookRoomCommand bookRoomCommand) {
        Room room = roomRepository.get(bookRoomCommand.getRoomNr());
        BookingNr bookingNr = new BookingNr(idCreator.generateId());
        room.addEvent(new RoomBookedEvent(room.roomNr(), bookingNr, bookRoomCommand.getStartDate(), bookRoomCommand.getEndDate()));
        roomRepository.save(room);
    }

    public void apply(CancelRoomCommand cancelRoomCommand) {
        Room room = roomRepository.get(cancelRoomCommand.getRoomNr());
        RoomCancelledEvent roomCancelledEvent = new RoomCancelledEvent(cancelRoomCommand.getRoomNr(), cancelRoomCommand.getBookingNr());
        room.addEvent(roomCancelledEvent);
        roomRepository.save(room);
    }

    public void apply(RoomMaxCapacityCommand roomMaxCapacityCommand) {
        Room room = roomRepository.get(roomMaxCapacityCommand.getRoomNr());
        MaxRoomCapacitySetEvent roomCapacitySetEvent = new MaxRoomCapacitySetEvent(roomMaxCapacityCommand.getRoomNr(), roomMaxCapacityCommand.getMaxCapacity());
        room.addEvent(roomCapacitySetEvent);
        roomRepository.save(room);
    }
}
