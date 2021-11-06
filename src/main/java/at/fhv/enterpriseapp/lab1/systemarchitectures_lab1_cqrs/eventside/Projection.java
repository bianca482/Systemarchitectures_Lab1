package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;

public interface Projection {
    void receiveEvent(Event event);
}
