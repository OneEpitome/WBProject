package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.repository.SeatRepository;
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
