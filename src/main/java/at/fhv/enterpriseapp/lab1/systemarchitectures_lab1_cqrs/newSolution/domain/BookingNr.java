package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import java.util.Objects;

public class BookingNr {

    String _number;

    public BookingNr(String number) {
        _number = number;
    }

    public String number() {
        return _number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingNr roomNr = (BookingNr) o;
        return _number == roomNr._number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_number);
    }


}
