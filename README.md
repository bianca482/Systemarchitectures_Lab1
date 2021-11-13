# Systemarchitectures_Lab1
CQRS and Event Sourcing project for the course Systemarchitectures.

## How to use
In this project, there are three Spring Boot applications which run on 
separate ports:

- EventSide (Port 8080)
- WriteSide (Port 8081)
- ReadSide (Port 8082)

The first application you need to start is the EventSide.
After that, run the ReadSide, and at last, the WriteSide of the application.

Start, by visiting localhost:8080. There, you will see where how to access the 
Command-Side as well as the Query-Side of the application.

- Commands: localhost:8081/html/command.html
- Queries: localhost:8082/html/query.html

##Kommunikation zwischen den Applikationen
WriteSide: Über den EventPublisher werden die Events an den Rest Controller der EventSide übertragen



##Testfälle
Buchung erstellen
Buchung mit CheckInDate in der Vergangenheit
Buchung mit CheckInDate nach CheckOutDate
Buchung mit Raum erstellen, der zur gewünschten Zeit bereits belegt ist
Falsche Reservierungsnummer
Buchung der Vergangenheit erstellen und versuchen, diese zu canceln -> wirft InvalidCancelRoomCommandException
Buchung canceln

FreeRoomsQuery:
Anschließender Such-Zeitraum wird von 01.02.2022 - 07.02.2022 für 2 Personen sein
Räume suchen, die im angegebenen Zeitraum frei sind --> Nummer 1-9, 17 und 19 sind frei

GetBookingQuery:
Buchung suchen, die es gibt
Buchung suchen, bei welcher jeweils GuestId oder RoomNr nicht stimmt

