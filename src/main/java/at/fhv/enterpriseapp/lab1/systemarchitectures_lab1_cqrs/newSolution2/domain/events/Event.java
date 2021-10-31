package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.application.RandomIDCreatorImpl;
import java.time.LocalDateTime;

public abstract class Event {
    final LocalDateTime createdAt = LocalDateTime.now();
    final String id = new RandomIDCreatorImpl().generateId();

    @Override
    public String toString() {
        return "Event: " +
                "createdAt=" + createdAt +
                ", id='" + id;
    }
}
