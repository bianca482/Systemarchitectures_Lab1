package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Booking;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Configuration
public class ReadSide {

    @Autowired
    private BookingReadRepository readRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReadSide.class, args);
    }

//    @Bean
//    public CommandLineRunner run() {
//        return args -> {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            BookingReadService bookingReadService = new BookingReadServiceImpl(readRepository);
////            Optional<Booking> booking = bookingReadService.handleQuery(new GetBookingQuery(new RoomNr(2), new GuestId("123")));
////            System.out.println(booking.isPresent());
//
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
//                    System.out.println("From?");
//                    String input = scanner.nextLine();
//                    LocalDateTime checkInDate = LocalDate.parse(input, formatter).atTime(0, 0);
//
//                    System.out.println("To?");
//                    input = scanner.nextLine();
//                    LocalDateTime checkOutDate = LocalDate.parse(input, formatter).atTime(0, 0);
//
//                    List<Booking> bookingsInTimeRange = bookingReadService.handleQuery(new GetBookingsInTimeRangeQuery(checkInDate, checkOutDate));
//                    System.out.println("Number of bookings (expected 4): " + bookingsInTimeRange.size());
//
//                } else if (query.equals("FreeRooms")) {
//                    System.out.println("From?");
//                    String input = scanner.nextLine();
//                    LocalDateTime checkInDate = LocalDate.parse(input, formatter).atTime(0, 0);
//
//                    System.out.println("To?");
//                    input = scanner.nextLine();
//                    LocalDateTime checkOutDate = LocalDate.parse(input, formatter).atTime(0, 0);
//
//                    System.out.println("Number of guests");
//                    String numberOfGuests = scanner.nextLine();
//
////                    List<Room> freeRooms = bookingReadService.handleQuery(new FreeRoomsQuery(checkInDate, checkOutDate, Integer.parseInt(numberOfGuests)));
////                    System.out.println(freeRooms.size());
//                }
//            }
//
//        };
//    }
}
