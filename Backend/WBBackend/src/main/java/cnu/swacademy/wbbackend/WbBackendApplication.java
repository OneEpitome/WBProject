package cnu.swacademy.wbbackend;

import cnu.swacademy.wbbackend.domain.seat.Seat;
import cnu.swacademy.wbbackend.domain.seat.SeatService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WbBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbBackendApplication.class, args);
    }
}
