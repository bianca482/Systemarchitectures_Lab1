package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private ReservationNr _reservationNr;
    private LocalDate _checkInDate;
    private LocalDate _checkOutDate;
    private int _guestCount;
    private String _guestName;
    private String _guestId;
    private Room _room;

    public Booking(ReservationNr reservationNr, LocalDate checkInDate, LocalDate checkOutDate, int guestCount, String guestName, String guestId) {
        _reservationNr = reservationNr;
        _checkInDate = checkInDate;
        _checkOutDate = checkOutDate;
        _guestCount = guestCount;
        _guestName = guestName;
        _guestId = guestId;
    }

    public ReservationNr get_reservationNr() {
        return _reservationNr;
    }

    public LocalDate get_checkInDate() {
        return _checkInDate;
    }

    public LocalDate get_checkOutDate() {
        return _checkOutDate;
    }

    public int get_guestCount() {
        return _guestCount;
    }

    public String get_guestName() {
        return _guestName;
    }

    public String get_guestId() {
        return _guestId;
    }

    public Room get_room() {
        return _room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return _guestCount == booking._guestCount && Objects.equals(_reservationNr, booking._reservationNr) && Objects.equals(_checkInDate, booking._checkInDate) && Objects.equals(_checkOutDate, booking._checkOutDate) && Objects.equals(_guestName, booking._guestName) && Objects.equals(_guestId, booking._guestId) && Objects.equals(_room, booking._room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_reservationNr, _checkInDate, _checkOutDate, _guestCount, _guestName, _guestId, _room);
    }
}
