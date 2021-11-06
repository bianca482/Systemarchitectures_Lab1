package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidCancelRoomCommandException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;

public interface BookingWriteService {
    void applyBookRoomCommand(BookRoomCommand bookRoomCommand) throws RoomOccupiedException, InvalidTimeRangeException;
    void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand) throws InvalidCancelRoomCommandException;
}
