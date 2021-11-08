package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model;

import java.util.Objects;

public class RoomNr {
    private int number;

    public RoomNr() {

    }

    public RoomNr(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) { this.number = number; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomNr roomNr = (RoomNr) o;
        return number == roomNr.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "RoomNr{" +
                "_number=" + number +
                '}';
    }
}
