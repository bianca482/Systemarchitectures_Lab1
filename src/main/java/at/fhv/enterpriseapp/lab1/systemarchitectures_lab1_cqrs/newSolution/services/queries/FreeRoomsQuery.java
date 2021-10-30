package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.queries;

import java.time.LocalDateTime;

public class FreeRoomsQuery {

    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private int NumberOfPeoples;

    public FreeRoomsQuery(LocalDateTime startTime, LocalDateTime endTime, int numberOfPeoples) {
        StartTime = startTime;
        EndTime = endTime;
        NumberOfPeoples = numberOfPeoples;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public int getNumberOfPeoples() {
        return NumberOfPeoples;
    }
}
