package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain;

public class InvalidCancelRoomCommandException extends Exception {
    public InvalidCancelRoomCommandException() {
        super("No booking for given reservation number found or CheckOutDate has already passed. Booking could not be cancelled.");
    }
}
