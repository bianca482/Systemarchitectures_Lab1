package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.write;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.PubSubService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.Publisher;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.EventRepository;

import java.util.HashMap;
import java.util.Map;

public class BookingWriteRepository implements Publisher {
    private EventRepository _eventRepository;
    private Map<String, Booking> _store;
    private PubSubService _pubSubService;

    public BookingWriteRepository(EventRepository eventRepository, PubSubService pubSubService) {
        _store = new HashMap<>();
        _eventRepository = eventRepository;
        _pubSubService = pubSubService;
    }

    public void addBooking(ReservationNr reservationNr, Booking booking) {
        RoomBookedEvent event = new RoomBookedEvent(booking.roomNr(), reservationNr, booking.checkInDate(), booking.checkOutDate(), booking.guestId());
        _eventRepository.addEvent(event);
        _store.put(reservationNr.number(), booking);
        publish(event);
    }

    public void cancelBooking(ReservationNr reservationNr) {
        RoomCancelledEvent event = new RoomCancelledEvent(reservationNr);
        _eventRepository.addEvent(event);
        _store.remove(reservationNr.number());
        publish(event);
    }

    @Override
    public void publish(Event event) {
        _pubSubService.addToQueue(event);
        _pubSubService.broadcast();
    }
}
