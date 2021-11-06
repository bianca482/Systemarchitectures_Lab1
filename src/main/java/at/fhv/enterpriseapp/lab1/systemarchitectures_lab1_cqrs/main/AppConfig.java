package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.main;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.infrastructure.EventRepository;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.writeside.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EventPublisher eventPublisher() {
        return new EventPublisher();
    }
    @Bean
    public EventRepository eventRepository() {
        return new EventRepository();
    }
}
