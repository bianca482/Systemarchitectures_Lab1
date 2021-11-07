package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.RoomReadRepository;

import java.util.LinkedList;
import java.util.List;

public class RoomReadServiceImpl implements RoomReadService {
    private RoomReadRepository _roomReadRepository;
    private BookingReadRepository _bookingReadRepository;

    public RoomReadServiceImpl(RoomReadRepository roomReadRepository, BookingReadRepository bookingReadRepository) {
        _roomReadRepository = roomReadRepository;
        _bookingReadRepository = bookingReadRepository;
    }

    // Prüft, welche Zimmer für die angefragten Daten verfügbar sind
    @Override
    public List<Room> handleQuery(FreeRoomsQuery freeRoomsQuery) {
        List<Room> roomList = _roomReadRepository.getAllRooms();
        List<Room> freeRooms = new LinkedList<>();

        for (Room room : roomList) {
            //Prüfen, ob der Raum genügend Platz hat
            if (room.maxCapacity() >= freeRoomsQuery.numberOfGuests()) {
                List<Booking> allBookings = _bookingReadRepository.getAllBookings(room.roomNr());
                boolean roomIsFree = true;
                if (allBookings != null) {
                    for (Booking booking : allBookings) {
                        if (!((booking.checkOutDate().isBefore(freeRoomsQuery.checkInTime()) || booking.checkInDate().isAfter(freeRoomsQuery.checkOutTime())))) {
                            roomIsFree = false;
                        }
                    }
                }
                if (roomIsFree) {
                    freeRooms.add(room);
                }
            }
        }
        return freeRooms;
    }
}
