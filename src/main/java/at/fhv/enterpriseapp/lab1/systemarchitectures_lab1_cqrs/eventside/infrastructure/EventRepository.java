package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.Projection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;

import java.util.LinkedList;
import java.util.List;

public class EventRepository {
    private List<Event> _events;
    private List<Projection> _subscribedProjections = new LinkedList<>();

    public EventRepository() {
        _events = new LinkedList<>();
    }

    public void addEvent(Event e) {
        _events.add(e);
        publishEvent(e);
    }

    public List<Event> getEvents() {
        return _events;
    }

    public void subscribeProjection(Projection projection) {
        _subscribedProjections.add(projection);
    }

    private void publishEvent(Event event) {
        for (Projection projection : _subscribedProjections) {
            projection.receiveEvent(event);
        }
    }
}
