package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Model;

import java.util.Objects;

public class RoomAssignment {
    private ReservationNr _reservationNr;
    private RoomNr _roomNr;

    public RoomAssignment(ReservationNr _reservationNr, RoomNr _roomNr) {
        this._reservationNr = _reservationNr;
        this._roomNr = _roomNr;
    }

    public ReservationNr reservationNr() {
        return _reservationNr;
    }

    public RoomNr roomNr() {
        return _roomNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAssignment that = (RoomAssignment) o;
        return Objects.equals(_reservationNr, that._reservationNr) && Objects.equals(_roomNr, that._roomNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_reservationNr, _roomNr);
    }
}
