package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.Event;

import java.util.LinkedList;
import java.util.List;

public class EventRepository {
    private List<Event> _events;

    public EventRepository() {
        _events = new LinkedList<>();
    }

    public void addEvent(Event e) {
        _events.add(e);
    }

    public List<Event> getEvents() { return _events; }
}
