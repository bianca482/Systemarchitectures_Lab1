package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.InvalidCancelRoomCommandException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingWriteServiceImpl implements BookingWriteService {
    private BookingWriteRepository _writeRepository;
    private BookingReadService _bookingReadService;
    private RandomIDCreator _idCreator;

    // ToDo: Darf man auf den ReadService zugreifen bzw. diesen über den Konstruktur mitgeben?
    public BookingWriteServiceImpl(BookingWriteRepository writeRepository, BookingReadService bookingReadService) {
        _writeRepository = writeRepository;
        _bookingReadService = bookingReadService;
        _idCreator = new RandomIDCreatorImpl();
    }

    public void applyBookRoomCommand(BookRoomCommand bookRoomCommand) throws RoomOccupiedException, InvalidTimeRangeException {
        // Prüfen, ob das CheckOutDate nach dem CheckInDate ist und ob das CheckInDate nicht in der Vergangenheit liegt
        if (bookRoomCommand.checkOutDate().isBefore(bookRoomCommand.checkInDate()) || bookRoomCommand.checkInDate().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new InvalidTimeRangeException();
        }

        // Check if booking could be created according to specified roomNr and time range
        List<Booking> bookings = _bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(bookRoomCommand.checkInDate(), bookRoomCommand.checkOutDate()));
        for (Booking booking : bookings) {
            if (booking.roomNr().equals(bookRoomCommand.roomNr())) {
                if (!(booking.checkOutDate().isBefore(bookRoomCommand.checkInDate()) || booking.checkInDate().isAfter(bookRoomCommand.checkOutDate()))) {
                    throw new RoomOccupiedException();
                }
            }
        }

        ReservationNr reservationNr = new ReservationNr(_idCreator.generateId());
        Booking booking = new Booking(bookRoomCommand.roomNr(), reservationNr, bookRoomCommand.checkInDate(), bookRoomCommand.checkOutDate(), bookRoomCommand.guestId());
        _writeRepository.addBooking(reservationNr, booking);
    }

    public void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand) throws InvalidCancelRoomCommandException {
        // Prüfen, ob es eine Buchung mit der angegebenen Reservierungsnummer gibt
        List<Booking> bookings = _bookingReadService.getAllBookings();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.reservationNr().equals(cancelRoomCommand.reservationNr())) {
                // Prüfen, ob das CheckOutDate in der Vergangenheit liegt
                if (!booking.checkOutDate().isBefore(LocalDateTime.now())) {
                    found = true;
                }
            }
        }
        if (!found) {
            throw new InvalidCancelRoomCommandException();
        }
        _writeRepository.cancelBooking(cancelRoomCommand.reservationNr());
    }
}
