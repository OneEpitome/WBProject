package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.dto.SeatCreationDTO;
import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.exception.HallNotFoundException;
import cnu.swacademy.wbbackend.exception.SeatNotFoundException;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * SeatService provides the business logic for managing seats.
 */
@RequiredArgsConstructor
@Service
public class SeatService {

    /**
     * The SeatRepository used for database operations related to seats.
     */
    private final SeatRepository seatRepository;

    /**
     * The HallRepository used for database operations related to halls.
     */
    private final HallRepository hallRepository;

    /**
     * Saves a seat to the database with the hall associated with it.
     *
     * @param seatDTO the DTO object representing the seat and the hall it belongs to.
     * @return the saved seat with its generated ID.
     * @throws HallNotFoundException if the hall with the given name is not found.
     */
    public Seat save(SeatCreationDTO seatDTO) {
        String hallName = seatDTO.getHallName();
        Hall hall = hallRepository.findByName(hallName)
                .orElseThrow(() -> new HallNotFoundException(hallName + " is Not Found"));

        Seat seat = new Seat();
        seat.setSeatName(seatDTO.getSeatName());
        seat.setHall(hall);

        return seatRepository.save(seat);
    }

    public Seat findById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException("Seat ID : " + id + " is not found"));
    }
}
