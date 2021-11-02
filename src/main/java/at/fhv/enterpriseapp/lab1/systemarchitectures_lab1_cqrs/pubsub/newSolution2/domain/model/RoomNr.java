package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model;

import java.util.Objects;

public class RoomNr {
    private int _number;

    public RoomNr(int number) {
        _number = number;
    }

    public int number() {
        return _number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomNr roomNr = (RoomNr) o;
        return _number == roomNr._number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_number);
    }
}
