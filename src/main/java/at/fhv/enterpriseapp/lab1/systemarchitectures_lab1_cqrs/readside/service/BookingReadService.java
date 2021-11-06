package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;

import java.util.List;
import java.util.Optional;

public interface BookingReadService {
    List<Booking> handleQuery(GetBookingsInTimeRangeQuery getBookingsInTimeRangeQuery);
    Optional<Booking> handleQuery(GetBookingQuery getBookingQuery);
    List<Booking> getAllBookings();
}
