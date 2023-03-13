package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.hall.HallService;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import cnu.swacademy.wbbackend.domain.seat.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/seat")
@RestController
public class SeatController {

    private final SeatService seatService;
    private final HallService hallService;

    @PostMapping("/create/{hallId}")
    public Seat create(@ModelAttribute Seat seat, @PathVariable Long hallId) {
        Hall hall = hallService.findById(hallId);
        seat.setHall(hall);

        /*
        * SeatService 에 hallId 과 seat 을 argument 로 받는 save 메소드 추가 ?
        * */

        return seatService.save(seat);
    }
}
