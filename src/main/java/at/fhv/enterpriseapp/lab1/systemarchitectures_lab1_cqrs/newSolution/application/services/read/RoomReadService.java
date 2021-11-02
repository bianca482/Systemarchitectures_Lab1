package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.application.services.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.BookingProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries.FreeRoomsQuery;

import java.util.LinkedList;
import java.util.List;

public class RoomReadService {

    private BookingProjection _BookingProjection;
    private RoomProjection _roomProjection;

    public RoomReadService(BookingProjection bookingProjection, RoomProjection roomProjection) {
        _BookingProjection = bookingProjection;
        _roomProjection = roomProjection;
    }

    // GetFreeRooms (Parameter: Zeitraum, Anzahl Personen): Zeigt die verfügbaren Zimmer für die angefragten Daten an
    public List<Room> query(FreeRoomsQuery freeRoomsQuery) {
        List<Room> roomList = _roomProjection.getAllRooms();
        List<Room> freeRooms = new LinkedList<>();

        for (Room room : roomList) {
            if (room.maxCapacity() >= freeRoomsQuery.numberOfGuests()) {
                List<Booking> allBookings = _BookingProjection.getAllBookings(room.roomNr());
                for (Booking booking : allBookings) {
                    if (!((booking.checkInTime().isAfter(freeRoomsQuery.checkInTime()) && booking.checkInTime().isBefore(freeRoomsQuery.checkOutTime())) ||
                            (booking.checkOutTime().isAfter(freeRoomsQuery.checkInTime()) && booking.checkOutTime().isBefore(freeRoomsQuery.checkOutTime())))) {
                        freeRooms.add(room);
                    }
                }
            }
        }
        return freeRooms;
    }
}
