package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.service.HallService;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.service.SeatService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/seat")
@RestController
public class SeatController {

    private final SeatService seatService;
    private final HallService hallService;

    @PostMapping("/create")
    public Seat save(@ModelAttribute Seat seat, @RequestParam Long hallId) {
        Hall hall = hallService.findById(hallId);
        seat.setHall(hall);

        /*
        * SeatService 에 hallId 과 seat 을 argument 로 받는 save 메소드 추가 ?
        * */

        return seatService.save(seat);
    }

    @PostConstruct
    public void setup() {
        Hall hall = new Hall();
        hallService.save(hall);

        Seat seat = new Seat();
        seat.setHall(hall);
        seatService.save(seat);
    }
}
