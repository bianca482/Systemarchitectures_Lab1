package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
    @Bean
    public EventRepository eventRepository() {
        return new EventRepository();
    }
}
