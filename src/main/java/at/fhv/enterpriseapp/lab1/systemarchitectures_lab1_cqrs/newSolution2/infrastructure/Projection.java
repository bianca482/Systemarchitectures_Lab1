package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.events.Event;

public interface Projection {
    void receiveEvent(Event event);
}
