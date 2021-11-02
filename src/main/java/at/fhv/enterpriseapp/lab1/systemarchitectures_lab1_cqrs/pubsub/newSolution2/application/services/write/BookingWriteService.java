package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.write;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.write.BookingWriteRepository;

public class BookingWriteService {
    private BookingWriteRepository _writeRepository;
    private RandomIDCreator _idCreator;

    public BookingWriteService(BookingWriteRepository writeRepository) {
        _writeRepository = writeRepository;
        _idCreator = new RandomIDCreatorImpl();
    }

    public void applyBookRoomCommand(BookRoomCommand bookRoomCommand) {
        ReservationNr reservationNr = new ReservationNr(_idCreator.generateId());
        Booking booking = new Booking(bookRoomCommand.roomNr(), reservationNr, bookRoomCommand.checkInDate(), bookRoomCommand.checkOutDate(), bookRoomCommand.guestId());
        _writeRepository.addBooking(reservationNr, booking);
    }

    public void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand) {
        _writeRepository.cancelBooking(cancelRoomCommand.reservationNr());
    }
}
