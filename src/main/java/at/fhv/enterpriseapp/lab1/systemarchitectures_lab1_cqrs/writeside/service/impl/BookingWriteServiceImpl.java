package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.impl;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidCancelRoomCommandException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreator;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.impl.RandomIDCreatorImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookingWriteServiceImpl implements BookingWriteService {

    @Autowired
    private BookingWriteRepository writeRepository;

    @Autowired
    private BookingReadService bookingReadService;

    private RandomIDCreator idCreator = new RandomIDCreatorImpl();

    public BookingWriteServiceImpl() {

    }

    // ToDo: Darf man auf den ReadService zugreifen bzw. diesen über den Konstruktur mitgeben?
    public BookingWriteServiceImpl(BookingWriteRepository writeRepository, BookingReadService bookingReadService) {
        this.writeRepository = writeRepository;
        this.bookingReadService = bookingReadService;
        idCreator = new RandomIDCreatorImpl();
    }

    public void applyBookRoomCommand(BookRoomCommand bookRoomCommand) throws RoomOccupiedException, InvalidTimeRangeException {
        // Prüfen, ob das CheckOutDate nach dem CheckInDate ist und ob das CheckInDate nicht in der Vergangenheit liegt
        if (bookRoomCommand.getCheckOutDate().isBefore(bookRoomCommand.getCheckInDate()) || bookRoomCommand.getCheckInDate().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new InvalidTimeRangeException();
        }

        // Check if booking could be created according to specified roomNr and time range
        List<Booking> bookings = bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(bookRoomCommand.getCheckInDate(), bookRoomCommand.getCheckOutDate()));
        for (Booking booking : bookings) {
            if (booking.getRoomNr().equals(bookRoomCommand.getRoomNr())) {
                if (!(booking.getCheckOutDate().isBefore(bookRoomCommand.getCheckInDate()) || booking.getCheckInDate().isAfter(bookRoomCommand.getCheckOutDate()))) {
                    throw new RoomOccupiedException();
                }
            }
        }

        ReservationNr reservationNr = new ReservationNr(idCreator.generateId());
        Booking booking = new Booking(bookRoomCommand.getRoomNr(), reservationNr, bookRoomCommand.getCheckInDate(), bookRoomCommand.getCheckOutDate(), bookRoomCommand.getGuestId());
        writeRepository.addBooking(reservationNr, booking);
    }

    public void applyCancelRoomCommand(CancelRoomCommand cancelRoomCommand) throws InvalidCancelRoomCommandException {
        // Prüfen, ob es eine Buchung mit der angegebenen Reservierungsnummer gibt
        List<Booking> bookings = bookingReadService.getAllBookings();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.getReservationNr().equals(cancelRoomCommand.getReservationNr())) {
                // Prüfen, ob das CheckOutDate in der Vergangenheit liegt
                if (!booking.getCheckOutDate().isBefore(LocalDateTime.now())) {
                    found = true;
                }
            }
        }
        if (!found) {
            throw new InvalidCancelRoomCommandException();
        }
        writeRepository.cancelBooking(cancelRoomCommand.getReservationNr());
    }
}
