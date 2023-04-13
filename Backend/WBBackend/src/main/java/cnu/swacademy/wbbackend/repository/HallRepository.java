package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * HallRepository is a repository interface for managing {@link Hall} entities.
 * It extends JpaRepository to take advantage of Spring Data JPA's functionalities.
 */
@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    /**
     * Find a hall by its name.
     *
     * @param hallName the name of the hall to be searched.
     * @return an Optional containing the hall if found, otherwise an empty Optional.
     */
    Optional<Hall> findByName(String hallName);
}
