package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidCancelRoomCommandException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadServiceImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Optional;

@ComponentScan({"at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside"})
@ComponentScan({"at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure"})
@ComponentScan({"at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service"})
@ComponentScan({"at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside"})
@SpringBootApplication
@Configuration
public class WriteSide {

    public static void main(String[] args) {
        SpringApplication.run(WriteSide.class, args);
    }

    @Autowired
    BookingWriteServiceImpl bookingWriteService;

    @Autowired
    BookingReadRepository bookingReadRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            EventRepository eventRepository = new EventRepository();
            EventPublisher eventPublisher = new EventPublisher();
//            BookingReadRepository bookingReadRepository = this.bookingReadRepository;
//            BookingReadService bookingReadService = new BookingReadServiceImpl(bookingReadRepository);
//            BookingWriteRepository bookingWriteRepository = new BookingWriteRepository(eventRepository, eventPublisher);
//            BookingWriteService bookingWriteService = new BookingWriteServiceImpl(bookingWriteRepository, bookingReadService);

            //Command: BookRoom
            System.out.println("----------BookRoomCommand----------");
            try {
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 1, 0, 0), LocalDateTime.of(2022, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));
            } catch (RoomOccupiedException | InvalidTimeRangeException e) {
                e.printStackTrace();
            }

//            //Command: CancelBooking
//            System.out.println("----------CancelBookingCommand----------");
//            Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
//            System.out.println(booking.isEmpty());
//            booking.ifPresent(value -> {
//                try {
//                    bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(value.getReservationNr()));
//                } catch (InvalidCancelRoomCommandException e) {
//                    e.printStackTrace();
//                }
//            });

            System.out.println("----------GetBookingsInTimeRangeQuery----------");
            //Query: GetBookingsInTimeRangeQuery (Parameter: Zeitraum): Zeigt alle Buchungen im gewählten Zeitraum an
            try {
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 2, 0, 0), LocalDateTime.of(2022, 1, 9, 0, 0), new RoomNr(1), new GuestId("111")));
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 2, 0, 0), LocalDateTime.of(2022, 5, 9, 0, 0), new RoomNr(2), new GuestId("222")));
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 7, 0, 0), LocalDateTime.of(2022, 5, 8, 0, 0), new RoomNr(3), new GuestId("333")));
            } catch (RoomOccupiedException | InvalidTimeRangeException e) {
                e.printStackTrace();
            }
        };
    }
}
