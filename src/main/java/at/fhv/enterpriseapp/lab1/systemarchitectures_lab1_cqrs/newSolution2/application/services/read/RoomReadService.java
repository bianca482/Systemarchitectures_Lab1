package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.application.services.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure.read.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure.read.RoomReadRepository;

import java.util.LinkedList;
import java.util.List;

public class RoomReadService {
    private RoomReadRepository _roomReadRepository;
    private BookingReadRepository _bookingReadRepository;

    public RoomReadService(RoomReadRepository roomReadRepository, BookingReadRepository bookingReadRepository) {
        _roomReadRepository = roomReadRepository;
        _bookingReadRepository = bookingReadRepository;
    }

    // Prüft, welche Zimmer für die angefragten Daten verfügbar sind
    public List<Room> handleQuery(FreeRoomsQuery freeRoomsQuery) {
        List<Room> roomList = _roomReadRepository.getAllRooms();
        List<Room> freeRooms = new LinkedList<>();

        for (Room room : roomList) {
            if (room.maxCapacity() >= freeRoomsQuery.numberOfGuests()) {
                List<Booking> allBookings = _bookingReadRepository.getAllBookings(room.roomNr());
                if(allBookings != null ) {
                    for (Booking booking : allBookings) {
                        if (!((booking.checkInDate().isAfter(freeRoomsQuery.checkInTime()) && booking.checkInDate().isBefore(freeRoomsQuery.checkOutTime())) ||
                                (booking.checkOutDate().isAfter(freeRoomsQuery.checkInTime()) && booking.checkOutDate().isBefore(freeRoomsQuery.checkOutTime())))) {
                            freeRooms.add(room);
                        }
                    }
                }else{
                    freeRooms.add(room);
                }
            }
        }
        return freeRooms;
    }
}
