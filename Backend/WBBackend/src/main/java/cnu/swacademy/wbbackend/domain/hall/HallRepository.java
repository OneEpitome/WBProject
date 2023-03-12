package cnu.swacademy.wbbackend.domain.hall;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findHallByName(String hallName);
}
