package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.Event;

import java.util.LinkedList;
import java.util.List;

public abstract class Subscriber {
    private List<Event> _subscriberEvents = new LinkedList<>();

    public List<Event> getSubscriberEvents() {
        return _subscriberEvents;
    }

    public void setSubscriberEvents(List<Event> subscriberEvents) {
        _subscriberEvents = subscriberEvents;
    }

    public abstract void subscribe(PubSubService pubSubService);
}
