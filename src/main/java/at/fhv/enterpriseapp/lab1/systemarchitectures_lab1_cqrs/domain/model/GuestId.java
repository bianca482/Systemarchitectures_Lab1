package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model;

import java.util.Objects;

public class GuestId {
    String _id;

    public GuestId(String id) {
        _id = id;
    }

    public String id() {
        return _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestId that = (GuestId) o;
        return Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
