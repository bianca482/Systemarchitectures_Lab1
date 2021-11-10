package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.GetBookingsInTimeRangeQuery;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadService;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service.BookingReadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Configuration
public class ReadSide {

    @Autowired
    private BookingReadRepository readRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReadSide.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            BookingReadService bookingReadService = new BookingReadServiceImpl(readRepository);
            Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
            System.out.println(booking.isPresent());

//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.println("Possible queries: FreeRooms, GetBooking, GetBookingsInTimeRange");
//                String query = scanner.nextLine();
//
//                if (query.equals("GetBooking")) {
//                    System.out.println("RoomNr?");
//                    String roomNr = scanner.nextLine();
//                    System.out.println("GuestId?");
//                    String guestId = scanner.nextLine();
//
//                    Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(Integer.parseInt(roomNr)), new GuestId(guestId)));
//                    System.out.println(booking.isPresent());
//
//                } else if (query.equals("GetBookingsInTimeRange")) {
//                    DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-DD");
//
//                    System.out.println("From?");
//                    LocalDateTime from = null;
//                    String input = scanner.nextLine();
//                    if (!input.equals("") && input.trim().length() > 0) {
//                        from = LocalDateTime.parse(input, format);
//                        System.out.println(from);
//                    }
//
//                    System.out.println("To?");
//                    LocalDateTime to = null;
//                    input = scanner.nextLine();
//                    if (!input.equals("") && input.trim().length() > 0) {
//                        to = LocalDateTime.parse(String.format(input, format));
//                    }
//
//                    List<Booking> bookingsInTimeRange = bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(from, to));
//                    System.out.println("Number of bookings (expected 2): " + bookingsInTimeRange.size());
//
//                } else if (query.equals("FreeRooms")) {
//                    System.out.println("From?");
//                    String from = scanner.nextLine();
//                    System.out.println("To?");
//                    String to = scanner.nextLine();
//                    System.out.println("Number of guests");
//                    String numberOfGuests = scanner.nextLine();
//
//                    List<Booking> bookingsInTimeRange = bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(LocalDateTime.of(2022, 5, 1, 0, 0), LocalDateTime.of(2022, 5, 10, 0, 0)));
//                    System.out.println("Number of bookings (expected 2): " + bookingsInTimeRange.size());
//                }
//            }

        };
    }
}
