# Systemarchitectures_Lab1
CQRS und Event Sourcing Projekt für den Kurs Systemarchitekturen an der FH
Vorarlberg.

## Allgemeine Struktur
Das Projekt besteht aus insgesamt drei Spring Boot Applikationen, die
jeweils auf einem eigenen Port laufen. Die Applikationen wurden jeweils
in einem eigenen Ordner zusammengefasst und haben alle ein eigenes Main.

- EventSide (Port 8080)
- WriteSide (Port 8081)
- ReadSide (Port 8082)

<img src="src/main/resources/static/images/DomainModel.png" alt="Abbildung Domain Model"/>

## Verwendung
Als erstes muss die EventSide gestartet werden. Als Nächstes wird die 
ReadSide gestartet, die WriteSide muss die letzte Applikation sein, die
gestartet wird, da diese sowohl von der EventSide als auch von der ReadSide
abhängig ist.

Der Startpunkt der Applikation befindet sich im Browser unter localhost:8080. Dort
ist auch ersichtlich, wo die Commands und Queries ausgeführt werden können.

- Commands: localhost:8081/html/command.html
- Queries: localhost:8082/html/query.html

####Unterstützte Commands:

- BookRoomCommand (mit checkInDate, checkOutDate, roomNr, guestId), führt zu RoomBookedEvent
- CancelRoomCommand (mit reservationNr), führt zu RoomCancelledEvent

####Unterstützte Queries:

- FreeRoomsQuery (mit checkInDate, checkOutDate, numberOfGuests)
- GetBookingQuery (mit roomNr, guestId)
- GetBookingsInTimeRange (mit checkInDate, checkOutDate)


##Kommunikation zwischen den Applikationen
WriteSide: Über den EventPublisher werden die Events an den Rest Controller der EventSide 
übertragen.

EventSide: Im EventRepository werden die Subscriber gespeichert und 
über den ReadRestController benachrichtigt, wenn ein neues Event hinzugefügt wurde.


##Testfälle
Im Folgenden werden die durchgeführten Testfälle kurz beschrieben und
dokumentiert, wie das zu erwartende Ergebnis ist.

###Commands
- Gültige Buchung erstellen
  - RoomBookedEvent wurde erstellt
- Buchung mit CheckInDate in der Vergangenheit 
  - Wirf InvalidTimeRangeException
- Buchung mit CheckInDate nach CheckOutDate
  - Wirft InvalidTimeRangeException
- Buchung mit Raum erstellen, der zur gewünschten Zeit bereits belegt ist
  - Wirft RoomOccupiedException
- Buchung der Vergangenheit erstellen und versuchen, diese zu canceln
  - wirft InvalidCancelRoomCommandException
- Buchung canceln
  - RoomCancelledEvent wurde erstellt
- Falsche Reservierungsnummer
    - InvalidReservationNrException

###Queries
- FreeRoomsQuery:
  - Gültigen Zeitraum eingeben
    - z.B. Such-Zeitraum von 01.02.2022 - 07.02.2022 für 2 Personen -> Nummer 1-9, 17 und 19 sind frei
  - CheckInDate nach CheckOutDate
    - InvalidTimeRangeException

- GetBookingQuery:
  - Buchung suchen, die es gibt
    - Entsprechende Buchung wird zurückgeliefert
  - Buchung suchen, bei welcher jeweils GuestId oder RoomNr nicht stimmt
    - Optional.empty

