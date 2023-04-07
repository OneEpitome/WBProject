package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.service.HallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/halls")
@RestController
public class HallController {
    private final HallService hallService;

    @PostMapping
    public Hall createHall(@ModelAttribute Hall hall) {
        return hallService.save(hall);
    }


    // FindAll 로 가져온 뒤 자바에서 getName 일치하는 것 찾는 것과 where 절로 쿼리를 날리는 것 중 무엇이 빠를까?
    @GetMapping("/{hallName}")
    public Hall getHall(@PathVariable String hallName) {
        return hallService.findByName(hallName);
    }
}
