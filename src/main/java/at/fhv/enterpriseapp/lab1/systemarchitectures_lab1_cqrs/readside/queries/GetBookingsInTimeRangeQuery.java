package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries;

import java.time.LocalDateTime;

// GetBookings (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
public class GetBookingsInTimeRangeQuery {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public GetBookingsInTimeRangeQuery() {

    }

    public GetBookingsInTimeRangeQuery(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
