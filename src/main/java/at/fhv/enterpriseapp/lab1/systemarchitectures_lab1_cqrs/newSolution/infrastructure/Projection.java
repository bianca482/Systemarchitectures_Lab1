package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.Event;

public interface Projection {
    void receiveEvent(Event event);

}
