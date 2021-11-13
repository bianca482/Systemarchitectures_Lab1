package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingWriteRepository {

    @Autowired
    private EventPublisher publisher;

    public BookingWriteRepository() {

    }

    public void addBooking(ReservationNr reservationNr, Booking booking) {
        RoomBookedEvent event = new RoomBookedEvent(booking.getRoomNr(), reservationNr, booking.getCheckInDate(), booking.getCheckOutDate(), booking.getGuestId());
        publisher.publishEvent(event);
        System.out.println("event published");
    }

    public void cancelBooking(ReservationNr reservationNr) {
        RoomCancelledEvent event = new RoomCancelledEvent(reservationNr);
        publisher.publishEvent(event);
        System.out.println("event published");
    }
}
