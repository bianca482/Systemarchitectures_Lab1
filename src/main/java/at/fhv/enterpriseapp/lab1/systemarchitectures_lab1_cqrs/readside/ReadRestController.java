package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadServiceImpl;
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

//    @GetMapping(value = "/getFreeRooms", produces = "application/json")
//      TODO
//      query.html fertig machen --> JSON Daten holen und mittels javascript anzeigen
//    }

//    @GetMapping(value = "/getBooking", produces = "application/json")
//      TODO
//      query.html fertig machen --> JSON Daten holen und mittels javascript anzeigen
//    }

    @GetMapping(value = "/getBookingInTimeRange", produces = "application/json")
    public List<Booking> getBookingsInTimeRange(@RequestParam("check_in2") String checkInDateStr,
                                                @RequestParam("check_out2") String checkOutDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
        LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);

        List<Booking> bookingsInTimeRange = readService.handleQuery(new GetBookingsInTimeRangeQuery(checkInDate, checkOutDate));
        return bookingsInTimeRange;
    }

}

