package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadServiceImpl;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure.BookingWriteRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@SpringBootApplication
@Configuration
public class WriteSide {

    @Autowired
    private EventPublisher _publisher;

    public static void main(String[] args) {
        SpringApplication.run(WriteSide.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            EventRepository eventRepository = new EventRepository();
            EventPublisher eventPublisher = new EventPublisher();
            BookingReadRepository bookingReadRepository = new BookingReadRepository();
            BookingReadService bookingReadService = new BookingReadServiceImpl(bookingReadRepository);
            BookingWriteRepository bookingWriteRepository = new BookingWriteRepository(eventRepository, eventPublisher);
            BookingWriteService bookingWriteService = new BookingWriteServiceImpl(bookingWriteRepository, bookingReadService);

            eventRepository.subscribeProjection(bookingReadRepository);

            //Command: BookRoom
            System.out.println("----------BookRoomCommand----------");
            try {
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 1, 0, 0), LocalDateTime.of(2022, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));
            } catch (RoomOccupiedException | InvalidTimeRangeException e) {
                e.printStackTrace();
            }
        };
    }
}
