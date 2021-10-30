package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.queries;

import java.time.LocalDateTime;

public class FreeRoomsQuery {

    private LocalDateTime _checkInTime;
    private LocalDateTime _checkOutTime;
    private int _numberOfGuests;

    // GetFreeRooms (Parameter: Zeitraum, Anzahl Personen): Zeigt die verfügbaren Zimmer für die angefragten Daten an
    public FreeRoomsQuery(LocalDateTime checkInDate, LocalDateTime checkOutDate, int numberOfGuests) {
        _checkInTime = checkInDate;
        _checkOutTime = checkOutDate;
        _numberOfGuests = numberOfGuests;
    }

    public LocalDateTime checkInTime() {
        return _checkInTime;
    }

    public LocalDateTime checkOutTime() {
        return _checkOutTime;
    }

    public int numberOfGuests() {
        return _numberOfGuests;
    }
}
