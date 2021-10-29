package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model;

import java.util.Objects;

public class ReservationNr {
    String _number;

    public ReservationNr(String number) {
        _number = number;
    }

    public String number() {
        return _number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationNr that = (ReservationNr) o;
        return Objects.equals(_number, that._number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_number);
    }
}
