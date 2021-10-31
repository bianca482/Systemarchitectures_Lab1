package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.RoomBooking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.RoomBookingProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries.BookingsQuery;

import java.util.LinkedList;
import java.util.List;

public class BookingReadService {

    private RoomBookingProjection _roomBookingProjection;

    public BookingReadService(RoomBookingProjection roomBookingProjection) {
        _roomBookingProjection = roomBookingProjection;
    }

    public List<RoomBooking> query(BookingsQuery bookingsQuery) {
        List<RoomBooking> roomBookings = _roomBookingProjection.getAllBookings();
        List<RoomBooking> result = new LinkedList<>();

        // ToDo: Logik überdenken/ nachprüfen
        for (RoomBooking roomBooking : roomBookings) {
            if (roomBooking.checkInTime().isAfter(bookingsQuery.checkInTime()) && roomBooking.checkInTime().isBefore(bookingsQuery.checkOutTime())) {
                result.add(roomBooking);
            } else if (roomBooking.checkOutTime().isAfter(bookingsQuery.checkInTime()) && roomBooking.checkOutTime().isBefore(bookingsQuery.checkOutTime())) {
                result.add(roomBooking);
            }
        }
        return result;
    }
}
