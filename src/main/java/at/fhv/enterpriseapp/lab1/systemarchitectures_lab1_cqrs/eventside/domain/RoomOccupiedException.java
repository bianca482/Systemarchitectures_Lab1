package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain;

public class RoomOccupiedException extends Exception {
    public RoomOccupiedException() {
        super("Room is already occupied in the specified time range.");
    }
}
