package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.command.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.command.CancelBookingCommand;

public interface BookingWriteService {

    void bookRoom(BookRoomCommand command);
    void cancelBooking(CancelBookingCommand command);
}
