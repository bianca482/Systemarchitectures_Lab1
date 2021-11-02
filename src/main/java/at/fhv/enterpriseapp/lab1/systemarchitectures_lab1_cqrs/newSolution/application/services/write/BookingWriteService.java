package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.application.services.write;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.application.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.application.implementation.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands.CancelRoomCommand;

public class BookingWriteService {

    private RoomRepository _roomRepository;
    private RandomIDCreator _idCreator = new RandomIDCreatorImpl();

    public BookingWriteService(RoomRepository roomRepository) {
        _roomRepository = roomRepository;
    }

    public void applyBookRoomCommand(BookRoomCommand bookRoomCommand) {
        Room room = _roomRepository.get(bookRoomCommand.roomNr());
        ReservationNr reservationNr = new ReservationNr(_idCreator.generateId());
        room.addRoomEvent(new RoomBookedEvent(room.roomNr(), reservationNr, bookRoomCommand.checkInDate(), bookRoomCommand.checkOutDate()));
        _roomRepository.save(room);
    }

    public void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand) {
        Room room = _roomRepository.get(cancelRoomCommand.roomNr());
        RoomCancelledEvent roomCancelledEvent = new RoomCancelledEvent(cancelRoomCommand.roomNr(), cancelRoomCommand.ReservationNr());
        room.addRoomEvent(roomCancelledEvent);
        _roomRepository.save(room);
    }
}
