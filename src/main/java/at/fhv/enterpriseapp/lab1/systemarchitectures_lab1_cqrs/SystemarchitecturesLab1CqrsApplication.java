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
        SpringApplication.run(SystemarchitecturesLab1CqrsApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
