package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.InvalidTimeRangeException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.exceptions.RoomOccupiedException;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.GuestId;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.commands.BookRoomCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class WriteRestController {

    @Autowired
    private BookingWriteService bookingWriteService;


    @PostMapping(value = "/book")
    public String subscribe(@RequestParam("checkInDate") String checkInDateStr,
                            @RequestParam("checkOutDate") String checkOutDateStr,
                            @RequestParam("roomNr") String roomNrStr,
                            @RequestParam("guestId") String guestIdStr
    ) throws InvalidTimeRangeException, RoomOccupiedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime checkInDate = LocalDate.parse(checkInDateStr, formatter).atTime(0, 0);
        LocalDateTime checkOutDate = LocalDate.parse(checkOutDateStr, formatter).atTime(0, 0);
        int roomNr = Integer.parseInt(roomNrStr);

        bookingWriteService.applyBookRoomCommand(new BookRoomCommand(checkInDate, checkOutDate, new RoomNr(roomNr), new GuestId(guestIdStr)));
        return "{'status':'ok'}";
    }

//  might be useful later
//    @RequestMapping(value = "/book",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public void createRole(@RequestBody MultiValueMap<String, String> formData) {
//        String check_in = formData.getFirst("check_in");
//        String check_out = formData.getFirst("check_out");
//        String room_nr = formData.getFirst("room_nr");
//        String guest_id = formData.getFirst("guest_id");
//        // your code goes here
//    }


}

