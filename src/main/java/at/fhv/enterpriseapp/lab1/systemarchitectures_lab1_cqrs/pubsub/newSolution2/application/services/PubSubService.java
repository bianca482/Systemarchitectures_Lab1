package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PubSubService {
    private List<Subscriber> _subscribers = new LinkedList<>();
    private Queue<Event> _eventQueue = new LinkedList<>();

    public void addToQueue(Event event) {
    _eventQueue.add(event);
    }

    public void addSubscriber(Subscriber subscriber) {
        _subscribers.add(subscriber);
    }

    public void broadcast() {
        if (_eventQueue.isEmpty()) {
            System.out.println("No events to display.");
        } else {
            while (!_eventQueue.isEmpty()) {
                Event nextEvent = _eventQueue.remove();
                for (Subscriber subscriber : _subscribers) {
                    List<Event> subscriberEvents = subscriber.getSubscriberEvents();
                    subscriberEvents.add(nextEvent);
                    subscriber.setSubscriberEvents(subscriberEvents);
                }
            }
        }
    }
}
