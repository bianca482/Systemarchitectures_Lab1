package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;

public class BookingWriteRepository {
    private EventRepository _eventRepository;

    public BookingWriteRepository(EventRepository eventRepository) {
        _eventRepository = eventRepository;
    }

    public void addBooking(ReservationNr reservationNr, Booking booking) {
        RoomBookedEvent event = new RoomBookedEvent(booking.roomNr(), reservationNr, booking.checkInDate(), booking.checkOutDate(), booking.guestId());
        _eventRepository.addEvent(event);
    }

    public void cancelBooking(ReservationNr reservationNr) {
        RoomCancelledEvent event = new RoomCancelledEvent(reservationNr);
        _eventRepository.addEvent(event);
    }
}
