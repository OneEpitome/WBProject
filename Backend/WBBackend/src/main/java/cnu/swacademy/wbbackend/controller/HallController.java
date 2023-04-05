package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.service.HallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/hall")
@RestController
public class HallController {

    private final HallService hallService;

    @PostMapping("/create")
    public Hall createHall(@ModelAttribute Hall hall) {
        return hallService.save(hall);
    }

    @GetMapping("/{hallName}")
    public Hall readHall(@PathVariable String hallName) {
        return hallService.findByName(hallName);
    }
}
