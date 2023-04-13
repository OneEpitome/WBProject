package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.service.HallService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * HallController handles HTTP requests related to hall management.
 */
@RequiredArgsConstructor
@RequestMapping("/api/halls")
@RestController
public class HallController {

    /**
     * The HallService used for handling business logic related to halls.
     */
    private final HallService hallService;

    /**
     * Creates a new hall.
     *
     * @param hall the hall to be created.
     * @return the created hall with its generated ID.
     */
    @PostMapping
    public Hall createHall(@RequestBody Hall hall) {
        return hallService.save(hall);
    }

    /**
     * Retrieves a hall by its name.
     *
     * @param hallName the name of the hall to be retrieved.
     * @return the hall with the given name.
     */
    @GetMapping("/{hallName}")
    public Hall getHall(@PathVariable String hallName) {
        return hallService.findByName(hallName);
    }

    // for Dev
    @PostConstruct
    public void setup() {
        Hall hall = new Hall();
        hall.setName("Junshimhwa-Hall");
        hallService.save(hall);
    }
}
