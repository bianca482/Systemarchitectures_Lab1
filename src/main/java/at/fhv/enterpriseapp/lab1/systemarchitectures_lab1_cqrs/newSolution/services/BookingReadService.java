package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.RoomBooking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.RoomBookingProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.queries.BookingsQuery;

import java.util.LinkedList;
import java.util.List;

public class BookingReadService {

    private RoomBookingProjection roomBookingProjection;

    public BookingReadService(RoomBookingProjection roomBookingProjection) {
        this.roomBookingProjection = roomBookingProjection;

    }

    public List<RoomBooking> query(BookingsQuery bookingsQuery) {
        List<RoomBooking> roomBookings = roomBookingProjection.getAllBookings();

        List<RoomBooking> result = new LinkedList<>();

        for (RoomBooking roomBooking : roomBookings) {
            if (roomBooking.getStartTime().isAfter(bookingsQuery.getStartTime()) && roomBooking.getStartTime().isBefore(bookingsQuery.getEndTime())) {
                result.add(roomBooking);
            } else if (roomBooking.getEndTime().isAfter(bookingsQuery.getStartTime()) && roomBooking.getEndTime().isBefore(bookingsQuery.getEndTime())) {
                result.add(roomBooking);
            }
        }

        return result;
    }

}
