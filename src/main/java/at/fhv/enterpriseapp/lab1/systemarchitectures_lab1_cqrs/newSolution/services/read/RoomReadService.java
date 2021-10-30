package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomBookingProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries.FreeRoomsQuery;

import java.util.LinkedList;
import java.util.List;

public class RoomReadService {

    private RoomBookingProjection _roomBookingProjection;
    private RoomProjection _roomProjection;

    public RoomReadService(RoomBookingProjection roomBookingProjection, RoomProjection roomProjection) {
        _roomBookingProjection = roomBookingProjection;
        _roomProjection = roomProjection;
    }

    // GetFreeRooms (Parameter: Zeitraum, Anzahl Personen): Zeigt die verfügbaren Zimmer für die angefragten Daten an
    public List<Room> query(FreeRoomsQuery freeRoomsQuery) {
        List<Room> roomList = _roomProjection.getAllRooms();
        List<Room> freeRooms = new LinkedList<>();

        for (Room room : roomList) {
            if (room.maxCapacity() >= freeRoomsQuery.numberOfGuests()) {
                List<RoomBooking> allBookings = _roomBookingProjection.getAllBookings(room.roomNr());
                for (RoomBooking roomBooking : allBookings) {
                    if (!((roomBooking.checkInTime().isAfter(freeRoomsQuery.checkInTime()) && roomBooking.checkInTime().isBefore(freeRoomsQuery.checkOutTime())) ||
                            (roomBooking.checkOutTime().isAfter(freeRoomsQuery.checkInTime()) && roomBooking.checkOutTime().isBefore(freeRoomsQuery.checkOutTime())))) {
                        freeRooms.add(room);
                    }
                }
            }
        }
        return freeRooms;
    }
}
