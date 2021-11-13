package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.Projection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.events.RoomCancelledEvent;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

public class EventRepository {
    private List<Event> events;
    private List<Projection> subscribedProjections = new LinkedList<>();
    private List<String> subscribedEndpoints = new LinkedList<>();

    public EventRepository() {
        events = new LinkedList<>();
        subscribedEndpoints.add("http://localhost:8082");
    }

    public void addEvent(Event e) {
        events.add(e);
        publishEventRest(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void subscribeProjection(Projection projection) {
        subscribedProjections.add(projection);
    }

    private void publishEventRest(Event event) {
        for (String endpoint : this.subscribedEndpoints) {
            WebClient localApiClient = WebClient.create(endpoint);

            if (event instanceof RoomBookedEvent) {
                localApiClient
                        .post()
                        .uri("/event/booked")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(Mono.just(event), Event.class)
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .block();
            } else if (event instanceof RoomCancelledEvent) {
                localApiClient
                        .post()
                        .uri("/event/cancelled")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(Mono.just(event), Event.class)
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .block();
            }
        }
    }

    private void publishEvent(Event event) {
        for (Projection projection : subscribedProjections) {
            projection.receiveEvent(event);
        }
    }
}
