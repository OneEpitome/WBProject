package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The SeatRepository interface provides methods to manage Seat entities in the database.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByHallNameAndSeatName(String hallName, String seatName);
}
