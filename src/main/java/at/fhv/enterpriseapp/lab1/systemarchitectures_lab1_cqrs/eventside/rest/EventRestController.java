package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.rest;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventRestController {

    @Autowired
    private EventRepository _eventRepository;

    @PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
    public boolean addEvent(@RequestBody Event event) {
        _eventRepository.addEvent(event);
        System.out.println("event received");
        return true;
    }

}
