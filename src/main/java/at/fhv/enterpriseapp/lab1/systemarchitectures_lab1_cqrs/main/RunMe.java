package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.main;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.InvalidCancelRoomCommandException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadServiceImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.RoomReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.RoomReadServiceImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteServiceImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.RoomReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RunMe {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        BookingReadRepository bookingReadRepository = new BookingReadRepository();
        BookingReadService bookingReadService = new BookingReadServiceImpl(bookingReadRepository);
        BookingWriteRepository bookingWriteRepository = new BookingWriteRepository(eventRepository);
        BookingWriteService bookingWriteService = new BookingWriteServiceImpl(bookingWriteRepository, bookingReadService);

        eventRepository.subscribeProjection(bookingReadRepository);

        RoomReadRepository roomReadRepository = new RoomReadRepository();
        RoomReadService roomReadService = new RoomReadServiceImpl(roomReadRepository, bookingReadRepository);

        //Commands
        //Command: BookRoom
        try {
            // InvalidTimeRangeException gets thrown (because CheckInDate is in the past)
            // bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 6, 1, 0, 0), LocalDateTime.of(2021, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));

            // InvalidTimeRangeException gets thrown (because CheckOutDate is before CheckInDate)
            // bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 10, 0, 0), LocalDateTime.of(2022, 6, 1, 0, 0), new RoomNr(2), new GuestId("123")));

            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 1, 0, 0), LocalDateTime.of(2022, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));
        } catch (RoomOccupiedException | InvalidTimeRangeException e) {
            e.printStackTrace();
        }

        //Command: CancelBooking
        Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
        booking.ifPresent(value -> {
            try {
                bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(value.reservationNr()));
            } catch (InvalidCancelRoomCommandException e) {
                e.printStackTrace();
            }
        });

//        // Wird InvalidCancelRoomCommandException, weil die Reservierungsnummer nicht stimmt
//        try {
//            bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(new ReservationNr("83948134")));
//        } catch (InvalidCancelRoomCommandException e) {
//            e.printStackTrace();
//        }


//        // Buchung der Vergangenheit erstellen und versuchen, diese zu canceln -> wirft InvalidCancelRoomCommandException
//        try {
//            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 6, 1, 0, 0), LocalDateTime.of(2021, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));
//        } catch (InvalidTimeRangeException | RoomOccupiedException e) {
//            e.printStackTrace();
//        }
//
//        Optional<Booking> booking2 = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
//        booking2.ifPresent(value -> {
//            try {
//                bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(value.reservationNr()));
//            } catch (InvalidCancelRoomCommandException e) {
//                e.printStackTrace();
//            }
//        });

        System.out.println(eventRepository.getEvents().toString());

        //Queries
        //Query: GetBookingsInTimeRangeQuery (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
        try {
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 2, 0, 0), LocalDateTime.of(2022, 1, 9, 0, 0), new RoomNr(1), new GuestId("111")));
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 2, 0, 0), LocalDateTime.of(2022, 5, 9, 0, 0), new RoomNr(2), new GuestId("222")));
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 7, 0, 0), LocalDateTime.of(2022, 5, 8, 0, 0), new RoomNr(3), new GuestId("333")));
        } catch (RoomOccupiedException | InvalidTimeRangeException e) {
            e.printStackTrace();
        }
        List<Booking> allBookings = bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(LocalDateTime.of(2022, 5, 1, 0, 0), LocalDateTime.of(2022, 5, 10, 0, 0)));
        System.out.println("Number of bookings: " + allBookings.size());
        System.out.println("All bookings: " + allBookings);

        //Query: GetFreeRooms (Parameter: Zeitraum, Anzahl Personen): Zeigt die verfügbaren Zimmer für die angefragten Daten an
        // ToDo: Testfälle bereitstellen, insbesondere Datumslogik prüfen (Ilona)
        List<Room> freeRooms = roomReadService.handleQuery(new FreeRoomsQuery(LocalDateTime.of(2021, 5, 1, 0, 0), LocalDateTime.of(2021, 5, 10, 0, 0), 1));
        System.out.println("Free rooms: " + freeRooms);

        // ToDo: Query GetBookingQuery testen (Bianca)
    }
}

