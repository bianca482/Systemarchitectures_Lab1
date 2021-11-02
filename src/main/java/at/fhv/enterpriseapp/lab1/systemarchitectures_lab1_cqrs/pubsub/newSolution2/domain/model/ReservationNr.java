package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model;

import java.util.Objects;

public class ReservationNr {
    private String _number;

    public ReservationNr(String number) {
        _number = number;
    }

    public String number() { return _number; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationNr roomNr = (ReservationNr) o;
        return _number.equals(roomNr._number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_number);
    }
}
