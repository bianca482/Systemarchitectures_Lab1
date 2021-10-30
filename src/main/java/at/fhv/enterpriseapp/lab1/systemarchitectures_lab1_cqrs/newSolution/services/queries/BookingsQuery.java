package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.queries;

import java.time.LocalDateTime;

public class BookingsQuery {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingsQuery(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
