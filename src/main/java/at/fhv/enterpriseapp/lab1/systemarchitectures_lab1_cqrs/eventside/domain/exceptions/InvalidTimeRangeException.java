package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions;

public class InvalidTimeRangeException extends Exception {
    public InvalidTimeRangeException() {
        super("Invalid CheckInDate or CheckOutDate.");
    }
}
