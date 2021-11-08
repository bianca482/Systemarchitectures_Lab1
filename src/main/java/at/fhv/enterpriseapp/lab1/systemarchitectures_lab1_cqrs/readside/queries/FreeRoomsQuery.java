package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries;

import java.time.LocalDateTime;

// GetFreeRooms (Parameter: Zeitraum, Anzahl Personen): Zeigt die verfügbaren Zimmer für die angefragten Daten an
public class FreeRoomsQuery {

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int numberOfGuests;

    public FreeRoomsQuery() {

    }

    public FreeRoomsQuery(LocalDateTime checkInDate, LocalDateTime checkOutDate, int numberOfGuests) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
