package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreatorImpl;

import java.time.LocalDateTime;

public abstract class Event {
    LocalDateTime createdAt = LocalDateTime.now();
    String id = new RandomIDCreatorImpl().generateId();

    public Event() {

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event: " +
                "createdAt=" + createdAt +
                ", id='" + id;
    }
}
