package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.implementation.BookingWriteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFacade {

    private ApplicationFacade() {}

    @Bean
    public static BookingWriteService bookingWriteService() {
        return new BookingWriteServiceImpl();
    }
}
