package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.infrastructure.BookingReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EventPublisher {

    private final WebClient localApiClient = WebClient.create("http://localhost:8080");

    // BookingReadServiceImpl nutzt BookingReadRepository & BookingReadServiceImpl wird von BookingWriteService genutzt
    @Autowired
    private BookingReadRepository bookingReadRepository;

    public EventPublisher() {
    }

    public Boolean publishEvent(Event event) {
        System.out.println(event);
        bookingReadRepository.receiveEvent(event);
        if (event instanceof RoomBookedEvent) {
            return localApiClient
                    .post()
                    .uri("/event/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(Mono.just(event), Event.class)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        }
        else if (event instanceof RoomCancelledEvent) {
            return localApiClient
                    .post()
                    .uri("/event/cancel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(Mono.just(event), Event.class)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        }
        return false;
    }
}
