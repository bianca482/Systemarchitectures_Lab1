package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Application;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Command.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Command.CancelBookingCommand;

public interface BookingWriteService {

    void bookRoom(BookRoomCommand command);
    void cancelBooking(CancelBookingCommand command);
}
