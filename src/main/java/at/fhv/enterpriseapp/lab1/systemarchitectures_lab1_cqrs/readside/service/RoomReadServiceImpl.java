package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.RoomReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class RoomReadServiceImpl implements RoomReadService {

    @Autowired
    private RoomReadRepository roomReadRepository;

    @Autowired
    private BookingReadRepository bookingReadRepository;

    public RoomReadServiceImpl() {

    }

    public RoomReadServiceImpl(RoomReadRepository roomReadRepository, BookingReadRepository bookingReadRepository) {
        this.roomReadRepository = roomReadRepository;
        this.bookingReadRepository = bookingReadRepository;
    }

    // Prüft, welche Zimmer für die angefragten Daten verfügbar sind
    @Override
    public List<Room> handleQuery(FreeRoomsQuery freeRoomsQuery) {
        List<Room> roomList = roomReadRepository.getAllRooms();
        List<Room> freeRooms = new LinkedList<>();

        for (Room room : roomList) {
            //Prüfen, ob der Raum genügend Platz hat
            if (room.getMaxCapacity() >= freeRoomsQuery.getNumberOfGuests()) {
                List<Booking> allBookings = bookingReadRepository.getAllBookings(room.getRoomNr());
                boolean roomIsFree = true;
                if (allBookings != null) {
                    for (Booking booking : allBookings) {
                        if (!((booking.getCheckOutDate().isBefore(freeRoomsQuery.getCheckInDate()) || booking.getCheckInDate().isAfter(freeRoomsQuery.getCheckOutDate())))) {
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
