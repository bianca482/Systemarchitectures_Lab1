package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

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
    private BookingWriteServiceImpl bookingWriteService;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            try {
                // InvalidTimeRangeException gets thrown (because CheckInDate is in the past)
                // bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2021, 6, 1, 0, 0), LocalDateTime.of(2021, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));

                // InvalidTimeRangeException gets thrown (because CheckOutDate is before CheckInDate)
                //bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 10, 0, 0), LocalDateTime.of(2022, 6, 1, 0, 0), new RoomNr(2), new GuestId("123")));
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 2, 0, 0), LocalDateTime.of(2022, 1, 9, 0, 0), new RoomNr(1), new GuestId("111")));
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 6, 1, 0, 0), LocalDateTime.of(2022, 6, 10, 0, 0), new RoomNr(2), new GuestId("123")));
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 2, 0, 0), LocalDateTime.of(2022, 5, 9, 0, 0), new RoomNr(2), new GuestId("222")));
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 5, 7, 0, 0), LocalDateTime.of(2022, 5, 8, 0, 0), new RoomNr(3), new GuestId("333")));

                // Buchung f??ngt vor dem Such-Zeitraum an und endet nach dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 28, 0, 0), LocalDateTime.of(2022, 2, 8, 0, 0), new RoomNr(10), new GuestId("1234")));
                // Buchung f??ngt vor dem Such-Zeitraum an und endet in dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 28, 0, 0), LocalDateTime.of(2022, 2, 3, 0, 0), new RoomNr(11), new GuestId("5678")));
                // Buchung f??ngt in dem Such-Zeitraum an und endet nach dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 2, 6, 0, 0), LocalDateTime.of(2022, 2, 9, 0, 0), new RoomNr(12), new GuestId("9101")));
                // Buchung f??ngt in dem Such-Zeitraum an und endet in dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 2, 2, 0, 0), LocalDateTime.of(2022, 2, 5, 0, 0), new RoomNr(13), new GuestId("1213")));
                // Buchung f??ngt vor dem Such-Zeitraum an und endet vor dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 1, 26, 0, 0), LocalDateTime.of(2022, 1, 28, 0, 0), new RoomNr(14), new GuestId("1415")));
                // Buchung f??ngt nach dem Such-Zeitraum an und endet nach dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 3, 1, 0, 0), LocalDateTime.of(2022, 3, 7, 0, 0), new RoomNr(15), new GuestId("1617")));
                // Buchung f??ngt im Such-Zeitraum an und endet im dem Such-Zeitraum
                bookingWriteService.applyBookRoomCommand(new BookRoomCommand(LocalDateTime.of(2022, 2, 1, 0, 0), LocalDateTime.of(2022, 2, 7, 0, 0), new RoomNr(16), new GuestId("1819")));

            } catch (RoomOccupiedException | InvalidTimeRangeException e) {
                e.printStackTrace();
            }
        };
    }
}
