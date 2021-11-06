package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.main;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.*;
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
        System.out.println("----------BookRoomCommand----------");
        try {
            // InvalidTimeRangeException gets thrown (because CheckInDate is in the past)
            // bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 6, 1, 0, 0), LocalDateTime.of(2021, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));

            // InvalidTimeRangeException gets thrown (because CheckOutDate is before CheckInDate)
            // bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 10, 0, 0), LocalDateTime.of(2022, 6, 1, 0, 0), new RoomNr(2), new GuestId("123")));

            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 1, 0, 0), LocalDateTime.of(2022, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));
        } catch (RoomOccupiedException | InvalidTimeRangeException e) {
            e.printStackTrace();
        }
        System.out.println(eventRepository.getEvents());

        //Command: CancelBooking
        System.out.println("----------CancelBookingCommand----------");
        Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
        booking.ifPresent(value -> {
            try {
                bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(value.reservationNr()));
            } catch (InvalidCancelRoomCommandException e) {
                e.printStackTrace();
            }
        });
        System.out.println(eventRepository.getEvents());

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

        //Queries
        System.out.println("----------GetBookingsInTimeRangeQuery----------");
        //Query: GetBookingsInTimeRangeQuery (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
        try {
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 2, 0, 0), LocalDateTime.of(2022, 1, 9, 0, 0), new RoomNr(1), new GuestId("111")));
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 2, 0, 0), LocalDateTime.of(2022, 5, 9, 0, 0), new RoomNr(2), new GuestId("222")));
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 7, 0, 0), LocalDateTime.of(2022, 5, 8, 0, 0), new RoomNr(3), new GuestId("333")));
        } catch (RoomOccupiedException | InvalidTimeRangeException e) {
            e.printStackTrace();
        }
        List<Booking> bookingsInTimeRange = bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(LocalDateTime.of(2022, 5, 1, 0, 0), LocalDateTime.of(2022, 5, 10, 0, 0)));
        System.out.println("Number of bookings (expected 2): " + bookingsInTimeRange.size());

        //Query: GetFreeRooms (Parameter: Zeitraum, Anzahl Personen): Zeigt die verfügbaren Zimmer für die angefragten Daten an
        System.out.println("----------GetFreeRoomsQuery----------");
        // ToDo: Testfälle bereitstellen, insbesondere Datumslogik prüfen (Ilona)
        List<Room> freeRooms = roomReadService.handleQuery(new FreeRoomsQuery(LocalDateTime.of(2021, 5, 1, 0, 0), LocalDateTime.of(2021, 5, 10, 0, 0), 1));
        System.out.println("Free rooms: " + freeRooms);

        //Query: GetBookings
        System.out.println("----------GetBookingsQuery----------");
        //Buchung suchen, die es gibt
        Optional<Booking> getBookingsResult = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(1), new GuestId("111")));
        System.out.println(getBookingsResult.isPresent());
        //Buchung suchen, bei welcher jeweils GuestId oder RoomNr nicht stimmt
        Optional<Booking> getBookingsResult2 = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("5675")));
        System.out.println(getBookingsResult2.isEmpty());
        Optional<Booking> getBookingsResult3 = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("111")));
        System.out.println(getBookingsResult3.isEmpty());
    }
}

