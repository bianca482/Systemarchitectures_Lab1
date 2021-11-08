package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.EventPublisher;

public class BookingWriteRepository {
    private EventRepository _eventRepository;
    private EventPublisher _publisher;

    public BookingWriteRepository(EventRepository eventRepository, EventPublisher publisher) {
        _eventRepository = eventRepository;
        _publisher = publisher;
    }

    public void addBooking(ReservationNr reservationNr, Booking booking) {
        RoomBookedEvent event = new RoomBookedEvent(booking.roomNr(), reservationNr, booking.checkInDate(), booking.checkOutDate(), booking.guestId());
        _publisher.publishEvent(event);
        System.out.println("event published");
    }

    public void cancelBooking(ReservationNr reservationNr) {
        RoomCancelledEvent event = new RoomCancelledEvent(reservationNr);
        _publisher.publishEvent(event);
        System.out.println("event published");
    }
}
