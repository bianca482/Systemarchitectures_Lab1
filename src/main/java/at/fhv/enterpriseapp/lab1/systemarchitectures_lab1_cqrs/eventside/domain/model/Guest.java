package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model;

public class Guest {
    private GuestId guestId;
    private String name;

    public Guest() {

    }

    public Guest(GuestId guestId, String name) {
        this.guestId = guestId;
        this.name = name;
    }

    public void setGuestId(GuestId guestId) {
        this.guestId = guestId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "_guestId=" + guestId +
                ", _name='" + name + '\'' +
                '}';
    }
}
