package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.exception.HallNotFoundException;
import cnu.swacademy.wbbackend.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * HallService provides the business logic for managing halls.
 */
@RequiredArgsConstructor
@Service
public class HallService {

    /**
     * The HallRepository used for database operations related to halls.
     */
    private final HallRepository hallRepository;

    /**
     * Saves a hall to the database.
     *
     * @param hall the hall to be saved.
     * @return the saved hall with its generated ID.
     */
    public Hall save(Hall hall) {
        return hallRepository.save(hall);
    }

    /**
     * Finds a hall by its name.
     *
     * @param hallName the name of the hall to be found.
     * @return the hall with the given name.
     * @throws RuntimeException if the hall with the given name is not found.
     */
    public Hall findByName(String hallName) {
        return hallRepository.findByName(hallName)
                .orElseThrow(() -> new HallNotFoundException(hallName + " is Not Found"));
    }

    /**
     * Finds a hall by its ID.
     *
     * @param hallId the ID of the hall to be found.
     * @return the hall with the given ID.
     * @throws RuntimeException if the hall with the given ID is not found.
     */
    public Hall findById(Long hallId) {
        return hallRepository.findById(hallId)
                .orElseThrow(() -> new HallNotFoundException("Hall ID : " + hallId + " is Not Found"));
    }
}
