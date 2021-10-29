package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Model.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SystemarchitecturesLab1CqrsApplication {

    public static void main(String[] args) {
        Room room1 = new Room(new RoomNr(1), RoomCategory.SINGLE_ROOM);
        Room room2 = new Room (new RoomNr(2), RoomCategory.SINGLE_ROOM);
        Room room3 = new Room (new RoomNr(3), RoomCategory.SINGLE_ROOM);

        Room room4 = new Room(new RoomNr(4), RoomCategory.DOUBLE_ROOM);
        Room room5 = new Room(new RoomNr(5), RoomCategory.DOUBLE_ROOM);
        Room room6 = new Room(new RoomNr(6), RoomCategory.DOUBLE_ROOM);

        Room room7 = new Room(new RoomNr(7), RoomCategory.FAMILY_ROOM);
        Room room8 = new Room (new RoomNr(8), RoomCategory.FAMILY_ROOM);
        Room room9 = new Room (new RoomNr(9), RoomCategory.FAMILY_ROOM);

        SpringApplication.run(SystemarchitecturesLab1CqrsApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
