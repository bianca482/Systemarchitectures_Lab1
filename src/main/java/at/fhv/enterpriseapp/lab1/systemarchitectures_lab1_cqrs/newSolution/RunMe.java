package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomBookingProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.read.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.read.RoomReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.write.BookingWriteService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.commands.RoomMaxCapacityCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries.BookingsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.write.RoomWriteService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class RunMe {

    public static void main(String args[]) {
        List<RoomNr> rooms = createRooms();
        RoomNr roomNr = rooms.get(0);
        RoomRepository roomRepository = new RoomRepository(rooms);

        // Read Model 1
        RoomBookingProjection roomBookingProjection = new RoomBookingProjection();
        roomRepository.subscribe(roomBookingProjection);

        // Read Model2
        RoomProjection roomInfoProjection = new RoomProjection();
        roomRepository.subscribe(roomInfoProjection);


        //-------------------------------------------------------------------------------
        // testing

        BookingWriteService bookingService = new BookingWriteService(roomRepository);
        // book command
        bookingService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 10, 1, 0, 0), LocalDateTime.of(2021, 10, 10, 0, 0), roomNr));

        RoomWriteService roomService = new RoomWriteService(roomRepository);
        // max capacity command
        roomService.applyRoomMaxCapacityCommand(new RoomMaxCapacityCommand(roomNr, 2));

        // Query 1
        BookingReadService bookingReadService = new BookingReadService(roomBookingProjection);
        BookingsQuery bookingsQuery = new BookingsQuery(LocalDateTime.of(2020, 10, 1, 0, 0), LocalDateTime.of(2023, 10, 10, 0, 0));
        List<RoomBooking> roomBooking = bookingReadService.query(bookingsQuery);

        // Query 2
        RoomReadService roomReadService = new RoomReadService(roomBookingProjection, roomInfoProjection);
        FreeRoomsQuery freeRoomsQuery = new FreeRoomsQuery(LocalDateTime.of(2020, 10, 2, 0, 0), LocalDateTime.of(2020, 10, 10, 0, 0), 1);
        List<Room> freeRooms = roomReadService.query(freeRoomsQuery);

        System.exit(0);

    }

    public static List<RoomNr> createRooms() {
        List<RoomNr> roomNrList = new LinkedList<>();

        for (int i = 0; i < 30; i++) {
            RoomNr roomNr = new RoomNr(i);
            roomNrList.add(roomNr);
        }
        return roomNrList;
    }
}
