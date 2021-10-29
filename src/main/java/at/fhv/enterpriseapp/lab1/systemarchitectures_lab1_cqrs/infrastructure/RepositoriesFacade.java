package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.infrastructure;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.repositories.BookingWriteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoriesFacade {

    @Bean
    public BookingWriteRepository bookingWriteRepository() {
        return new BookingWriteRepositoryImpl();
    }
}
