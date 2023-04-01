package cnu.swacademy.wbbackend.domain.seat;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }
}
