package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReadRestController {

    @Autowired
    private BookingReadRepository readRepository;

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

//    @GetMapping(value = "/getBookingInTimeRange", produces = "application/json")
//      TODO
//      query.html fertig machen --> JSON Daten holen und mittels javascript anzeigen
//    }

}

