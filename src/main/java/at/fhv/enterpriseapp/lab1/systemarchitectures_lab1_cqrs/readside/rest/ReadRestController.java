package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.rest;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.RoomReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ReadRestController {

    @Autowired
    private BookingReadRepository readRepository;

    @Autowired
    private BookingReadService readService;

    @Autowired
    private RoomReadService roomReadService;

    @PostMapping(value = "/event/booked", consumes = "application/json", produces = "application/json")
    public boolean subscribe(@RequestBody RoomBookedEvent bookedEvent) {
        System.out.println(bookedEvent);
        readRepository.receiveEvent(bookedEvent);
        System.out.println("received bookedEvent");
        return true;
    }

    @PostMapping(value = "/event/cancelled", consumes = "application/json", produces = "application/json")
    public boolean subscribe(@RequestBody RoomCancelledEvent cancelEvent) {
        System.out.println(cancelEvent);
        readRepository.receiveEvent(cancelEvent);
        System.out.println("received cancelEvent");
        return true;
    }

    @PostMapping(value = "/getFreeRooms", produces = "application/json")
    public List<Room> getFreeRooms(@RequestParam("checkInDate") String checkInDateStr,
                                   @RequestParam("checkOutDate") String checkOutDateStr,
                                   @RequestParam("numberOfGuests") int numberOfGuests) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
        LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);

        try {
            return roomReadService.handleQuery(new FreeRoomsQuery(checkInDate, checkOutDate, numberOfGuests));
        } catch (InvalidTimeRangeException e) {
            return Collections.emptyList();
        }
    }

    @PostMapping(value = "/getBooking", produces = "application/json")
    public Booking getBooking(@RequestParam("roomNr") int roomNr,
                              @RequestParam("guestId") String guestId) {

        Optional<Booking> booking = readService.handleQuery(new GetBookingQuery(new RoomNr(roomNr), new GuestId(guestId)));
        return booking.orElse(null);
    }

    @PostMapping(value = "/getBookingInTimeRange", produces = "application/json")
    public List<Booking> getBookingsInTimeRange(@RequestParam("checkInDate") String checkInDateStr,
                                                @RequestParam("checkOutDate") String checkOutDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
        LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);

        return readService.handleQuery(new GetBookingsInTimeRangeQuery(checkInDate, checkOutDate));
    }
}

