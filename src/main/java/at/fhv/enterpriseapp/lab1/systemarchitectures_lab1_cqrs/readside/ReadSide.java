package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ReadSide {

    public static void main(String[] args) {
        SpringApplication.run(ReadSide.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {

        };
    }
}
