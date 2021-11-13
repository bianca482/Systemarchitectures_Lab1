package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookingReadService {
    List<Booking> handleQuery(GetBookingsInTimeRangeQuery getBookingsInTimeRangeQuery);

    Optional<Booking> handleQuery(GetBookingQuery getBookingQuery);

    List<Booking> getAllBookings();
}
