package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.PubSubService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.Publisher;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.Subscriber;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.read.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.application.services.write.BookingWriteService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.model.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.domain.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.read.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.read.RoomReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.pubsub.newSolution2.infrastructure.write.BookingWriteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RunMe {
    public static void main(String[] args) {
        //Räume statisch im System hinterlegen
        RoomReadRepository roomReadRepository = new RoomReadRepository();
        List<Room> rooms = roomReadRepository.getAllRooms();
        for (Room r : rooms) {
            System.out.println(r.roomNr().number() + ", capacity: " + r.maxCapacity());
        }

        //Objekte erstellen
        PubSubService pubSubService = new PubSubService();
        EventRepository eventRepository = new EventRepository();
        BookingWriteRepository bookingWriteRepository = new BookingWriteRepository(eventRepository, pubSubService);
        BookingWriteService bookingWriteService = new BookingWriteService(bookingWriteRepository);

        BookingReadRepository bookingReadRepository = new BookingReadRepository(pubSubService);
        BookingReadService bookingReadService = new BookingReadService(bookingReadRepository);

        //Commands
        //Command: BookRoom
        bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 5, 1, 0, 0), LocalDateTime.of(2021, 5, 10, 0, 0), new RoomNr(2), new GuestId("123")));
        bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 5, 1, 0, 0), LocalDateTime.of(2021, 5, 10, 0, 0), new RoomNr(2), new GuestId("123")));
        //Command: CancelBooking --> dafür muss Publisher/ Subscriber bereits implementiert sein! (Read/ Write-Model synchronisieren)
        Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
        booking.ifPresent(value -> bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(value.reservationNr())));
        System.out.println(eventRepository.getEvents().toString());

        //Queries

        Publisher publisher = new BookingWriteRepository(eventRepository, pubSubService);
        Subscriber subscriber = new BookingReadRepository(pubSubService);
        System.out.println(subscriber.getSubscriberEvents());
        publisher.publish(new RoomCancelledEvent(new ReservationNr("123")));
        System.out.println(subscriber.getSubscriberEvents());

        // ToDo: BookRoomCommand -> Prüfung, ob Buchung erstellt werden darf (Raum frei?)
        // ToDo: Projector implementieren, evtl. Pub/Sub überflüssig?
        // ToDo: Queries ausprobieren
        // ToDo: Negativfälle insgesamt überprüfen

    }

}
