package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.implementation;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.BookingWriteService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.command.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.command.CancelBookingCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomAssignment;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.repositories.BookingWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingWriteServiceImpl implements BookingWriteService {

    public RandomIDCreator idCreator;

    //@Autowired
    //public BookingWriteRepository bookingWriteRepository;

    @Override
    public void bookRoom(BookRoomCommand command) {
        ReservationNr reservationNr = new ReservationNr(idCreator.generateId());
        GuestId guestId = new GuestId(idCreator.generateId());
        Booking booking = new Booking(reservationNr, command.checkInDate(), command.checkOutDate(), command.guestCount(), command.guestName(), guestId);
        RoomAssignment roomAssignment = new RoomAssignment(reservationNr, command.roomNr());

        //Booking oder RoomAssignment als Parameter?
        //bookingWriteRepository.bookRoom();
    }

    @Override
    public void cancelBooking(CancelBookingCommand command) {

    }
}
