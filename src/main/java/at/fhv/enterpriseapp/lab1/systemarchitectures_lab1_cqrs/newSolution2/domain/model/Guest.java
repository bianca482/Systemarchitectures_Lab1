package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.model;

public class Guest {
    private GuestId _guestId;
    private String _name;

    public Guest(GuestId guestId, String name) {
        _guestId = guestId;
        _name = name;
    }

    public GuestId guestId() {
        return _guestId;
    }

    public String name() {
        return _name;
    }
}
