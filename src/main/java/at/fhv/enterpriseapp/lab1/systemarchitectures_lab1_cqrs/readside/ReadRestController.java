package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReadRestController {

    @Autowired
    private BookingReadRepository readRepository;

    @PostMapping(value = "/event/booked", consumes = "application/json", produces = "application/json")
    public boolean subscribe(@RequestBody RoomBookedEvent bookedEvent) {
        System.out.println(bookedEvent);
        readRepository.receiveEvent(bookedEvent);
        System.out.println("subscribed");
        return true;
    }
}

