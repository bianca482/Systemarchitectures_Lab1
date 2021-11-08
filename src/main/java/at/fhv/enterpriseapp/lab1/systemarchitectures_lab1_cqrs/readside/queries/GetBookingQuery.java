package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;

public class GetBookingQuery {
    private RoomNr roomNr;
    private GuestId guestId;

    public GetBookingQuery() {

    }

    public GetBookingQuery(RoomNr roomNr, GuestId guestId) {
        this.roomNr = roomNr;
        this.guestId = guestId;
    }

    public RoomNr getRoomNr() {
        return roomNr;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public void setRoomNr(RoomNr roomNr) {
        this.roomNr = roomNr;
    }

    public void setGuestId(GuestId guestId) {
        this.guestId = guestId;
    }
}
