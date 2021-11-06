package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries;

import java.time.LocalDateTime;

// GetBookings (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
public class GetBookingsInTimeRangeQuery {
    private LocalDateTime _checkInDate;
    private LocalDateTime _checkOutDate;

    public GetBookingsInTimeRangeQuery(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        _checkInDate = checkInTime;
        _checkOutDate = checkOutTime;
    }

    public LocalDateTime checkInDate() {
        return _checkInDate;
    }

    public LocalDateTime checkOutDate() {
        return _checkOutDate;
    }
}
