package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.application.implementation.RandomIDCreatorImpl;
import java.time.LocalDateTime;

public abstract class Event {
    final LocalDateTime createdAt = LocalDateTime.now();
    final String id = new RandomIDCreatorImpl().generateId();
}
