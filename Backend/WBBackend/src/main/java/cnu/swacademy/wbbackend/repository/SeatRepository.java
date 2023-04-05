package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
