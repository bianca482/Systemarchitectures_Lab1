package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;

public interface BookingWriteService {
    void applyBookRoomCommand(BookRoomCommand bookRoomCommand) throws RoomOccupiedException;
    void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand);
}
