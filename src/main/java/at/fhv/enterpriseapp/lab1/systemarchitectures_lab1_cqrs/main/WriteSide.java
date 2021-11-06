package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.main;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.ReservationNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            RoomCancelledEvent event = new RoomCancelledEvent(new ReservationNr("reservation1"));
            System.out.println("event created");
            _publisher.publishEvent(event);
            System.out.println("event published");
        };
    }
}
