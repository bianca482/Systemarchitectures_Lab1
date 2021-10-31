package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.queries;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model.RoomNr;

public class GetBookingQuery {
    private RoomNr _roomNr;
    private GuestId _guestId;

    public GetBookingQuery(RoomNr roomNr, GuestId guestId) {
        _roomNr = roomNr;
        _guestId = guestId;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    public GuestId guestId() {
        return _guestId;
    }
}
