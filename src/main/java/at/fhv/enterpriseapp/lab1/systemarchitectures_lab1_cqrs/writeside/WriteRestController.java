package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidCancelRoomCommandException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.CancelRoomCommand;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service.BookingWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class WriteRestController {

    @Autowired
    private BookingWriteService bookingWriteService;

    @PostMapping(value = "/book", produces = "application/json")
    public String saveBooking(@RequestParam("checkInDate") String checkInDateStr,
                              @RequestParam("checkOutDate") String checkOutDateStr,
                              @RequestParam("roomNr") String roomNrStr,
                              @RequestParam("guestId") String guestIdStr
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
            LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);
            int roomNr = Integer.parseInt(roomNrStr);

            bookingWriteService.applyBookRoomCommand(new BookRoomCommand(checkInDate, checkOutDate, new RoomNr(roomNr), new GuestId(guestIdStr)));
            return "{\"status\":\"ok\"}";
        } catch (InvalidTimeRangeException | RoomOccupiedException ex) {
            return "{\"status\":\"failed\", \"info\":\"" + ex.getClass().getSimpleName() + "\"}";
        }
    }

    @PostMapping(value = "/cancel")
    public String cancelBooking(@RequestParam("reservationNr") String reservationNrStr
    ) throws InvalidCancelRoomCommandException {

        bookingWriteService.applyCancelRoomCommand(new CancelRoomCommand(new ReservationNr(reservationNrStr)));
        return "{'status':'ok'}";
    }
}

