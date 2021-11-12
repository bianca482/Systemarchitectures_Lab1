package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.EventPublisher;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RunMe {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        EventPublisher eventPublisher = new EventPublisher();
        BookingReadRepository bookingReadRepository = new BookingReadRepository();
        BookingReadService bookingReadService = new BookingReadServiceImpl(bookingReadRepository);
        BookingWriteRepository bookingWriteRepository = new BookingWriteRepository(eventRepository, eventPublisher);
        BookingWriteService bookingWriteService = new BookingWriteServiceImpl(bookingWriteRepository, bookingReadService);

        eventRepository.subscribeProjection(bookingReadRepository);

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
                bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(value.getReservationNr()));
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

        RoomReadRepository roomReadRepository = new RoomReadRepository();
        RoomReadService roomReadService = new RoomReadServiceImpl(roomReadRepository, bookingReadRepository);
        // Buchungen erstellen (Anschließender Such-Zeitraum wird von 01.02.2022 - 07.02.2022 für 2 Personen sein)
        try {
            // Buchung fängt vor dem Such-Zeitraum an und endet nach dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 28, 0, 0), LocalDateTime.of(2022, 2, 8, 0, 0), new RoomNr(30), new GuestId("1234")));
            // Buchung fängt vor dem Such-Zeitraum an und endet in dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 28, 0, 0), LocalDateTime.of(2022, 2, 3, 0, 0), new RoomNr(31), new GuestId("5678")));
            // Buchung fängt in dem Such-Zeitraum an und endet nach dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 2, 6, 0, 0), LocalDateTime.of(2022, 2, 9, 0, 0), new RoomNr(32), new GuestId("9101")));
            // Buchung fängt in dem Such-Zeitraum an und endet in dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 2, 2, 0, 0), LocalDateTime.of(2022, 2, 5, 0, 0), new RoomNr(33), new GuestId("1213")));
            // Buchung fängt vor dem Such-Zeitraum an und endet vor dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 26, 0, 0), LocalDateTime.of(2022, 1, 28, 0, 0), new RoomNr(34), new GuestId("1415")));
            // Buchung fängt nach dem Such-Zeitraum an und endet nach dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 3, 1, 0, 0), LocalDateTime.of(2022, 3, 7, 0, 0), new RoomNr(35), new GuestId("1617")));
            // Buchung fängt im Such-Zeitraum an und endet im dem Such-Zeitraum
            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 2, 1, 0, 0), LocalDateTime.of(2022, 2, 7, 0, 0), new RoomNr(36), new GuestId("1819")));
        } catch (RoomOccupiedException | InvalidTimeRangeException e) {
            e.printStackTrace();
        }
        // Räume suchen, die im angegebenen Zeitraum frei sind --> Nummer 34, 35 und 37 sind frei
        List<Room> freeRooms = roomReadService.handleQuery(new FreeRoomsQuery(LocalDateTime.of(2022, 2, 1, 0, 0), LocalDateTime.of(2022, 2, 7, 0, 0), 2));
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

