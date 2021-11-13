package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model;

import java.util.Objects;

public class ReservationNr {
    private String number;

    public ReservationNr() {

    }

    public ReservationNr(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationNr roomNr = (ReservationNr) o;
        return number.equals(roomNr.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "ReservationNr{" +
                "_number='" + number + '\'' +
                '}';
    }
}
