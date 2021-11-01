package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.application.services.read;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure.BookingProjection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries.BookingsQuery;

import java.util.LinkedList;
import java.util.List;

public class BookingReadService {

    private BookingProjection _BookingProjection;

    public BookingReadService(BookingProjection bookingProjection) {
        _BookingProjection = bookingProjection;
    }

    public List<Booking> query(BookingsQuery bookingsQuery) {
        List<Booking> bookings = _BookingProjection.getAllBookings();
        List<Booking> result = new LinkedList<>();

        // ToDo: Logik überdenken/ nachprüfen
        for (Booking booking : bookings) {
            if (booking.checkInTime().isAfter(bookingsQuery.checkInTime()) && booking.checkInTime().isBefore(bookingsQuery.checkOutTime())) {
                result.add(booking);
            } else if (booking.checkOutTime().isAfter(bookingsQuery.checkInTime()) && booking.checkOutTime().isBefore(bookingsQuery.checkOutTime())) {
                result.add(booking);
            }
        }
        return result;
    }
}
