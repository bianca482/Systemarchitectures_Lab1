package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries;

import java.time.LocalDateTime;

public class BookingsQuery {
    private LocalDateTime _checkInTime;
    private LocalDateTime _checkOutTime;

    // GetBookings (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
    public BookingsQuery(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        _checkInTime = checkInTime;
        _checkOutTime = checkOutTime;
    }

    public LocalDateTime checkInTime() {
        return _checkInTime;
    }

    public LocalDateTime checkOutTime() {
        return _checkOutTime;
    }
}
