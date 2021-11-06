package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.RoomReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;

public class BookingWriteServiceImpl implements BookingWriteService {
    private BookingWriteRepository _writeRepository;
    private BookingReadService _bookingReadService;
    private RoomReadService _roomReadService;
    private RandomIDCreator _idCreator;

    public BookingWriteServiceImpl(BookingWriteRepository writeRepository) {
        _writeRepository = writeRepository;
        _idCreator = new RandomIDCreatorImpl();
    }

    public void applyBookRoomCommand(BookRoomCommand bookRoomCommand) throws RoomOccupiedException {
//        List<Booking> bookings = _bookingReadService.handleQuery(new AllBookingsQuery(bookRoomCommand.checkInDate(), bookRoomCommand.checkOutDate()));
//
//        //Check if booking could be created according to specified roomNr and time range
//        for (Booking booking : bookings) {
//            if (booking.roomNr().equals(bookRoomCommand.roomNr())) {
//                if (!(booking.checkOutDate().isBefore(bookRoomCommand.checkInDate()) || booking.checkInDate().isAfter(bookRoomCommand.checkOutDate()))) {
//                    throw new RoomOccupiedException();
//                }
//            }
//        }
        ReservationNr reservationNr = new ReservationNr(_idCreator.generateId());
        Booking booking = new Booking(bookRoomCommand.roomNr(), reservationNr, bookRoomCommand.checkInDate(), bookRoomCommand.checkOutDate(), bookRoomCommand.guestId());
        _writeRepository.addBooking(reservationNr, booking);
    }

    public void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand) {
        _writeRepository.cancelBooking(cancelRoomCommand.reservationNr());
    }
}
