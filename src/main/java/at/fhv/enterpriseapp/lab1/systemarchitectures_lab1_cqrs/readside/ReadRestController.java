package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadServiceImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.RoomReadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    private BookingReadRepository readRepository;

    @Autowired
    private BookingReadServiceImpl readService;

    @Autowired
    private RoomReadServiceImpl roomReadService;

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

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(value = "/getFreeRooms", produces = "application/json")
    public List<Room> getFreeRooms(@RequestParam("checkInDate") String checkInDateStr,
                                   @RequestParam("checkOutDate") String checkOutDateStr,
                                   @RequestParam("numberOfGuests") int numberOfGuests) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
        LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);

        List<Room> freeRooms = roomReadService.handleQuery(new FreeRoomsQuery(checkInDate, checkOutDate, numberOfGuests));
        return freeRooms;
    }

//    @GetMapping(value = "/getBooking", produces = "application/json")
//      TODO
//      query.html fertig machen --> JSON Daten holen und mittels javascript anzeigen
//    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(value = "/getBookingInTimeRange", produces = "application/json")
    public List<Booking> getBookingsInTimeRange(@RequestParam("checkInDate") String checkInDateStr,
                                                @RequestParam("checkOutDate") String checkOutDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
        LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);

        List<Booking> bookingsInTimeRange = readService.handleQuery(new GetBookingsInTimeRangeQuery(checkInDate, checkOutDate));
        return bookingsInTimeRange;
    }
}

