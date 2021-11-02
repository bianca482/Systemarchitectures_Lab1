package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.queries;

import java.time.LocalDateTime;

// GetBookings (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
public class AllBookingsQuery {
    private LocalDateTime _checkInDate;
    private LocalDateTime _checkOutDate;

    public AllBookingsQuery(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
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
