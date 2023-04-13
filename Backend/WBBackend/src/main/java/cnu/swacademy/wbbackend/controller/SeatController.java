package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.dto.SeatCreationDTO;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.service.SeatService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * SeatController handles HTTP requests related to seats.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seats")
public class SeatController {

    /**
     * The SeatService used for business logic related to seats.
     */
    private final SeatService seatService;

    /**
     * Creates a new seat with the given name and hall name and saves it to the database.
     *
     * @param seatDTO the DTO object representing the seat and the hall it belongs to.
     * @return the ResponseEntity containing the saved seat and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<Seat> createSeat(@Validated @RequestBody SeatCreationDTO seatDTO) {
        Seat savedSeat = seatService.save(seatDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
    }

    // for dev
    @PostConstruct
    public void setup() {
        SeatCreationDTO seatCreationDTO = new SeatCreationDTO();
        seatCreationDTO.setHallName("Junshimhwa-Hall");
        seatCreationDTO.setSeatName("A1");
        seatService.save(seatCreationDTO);
    }
}
