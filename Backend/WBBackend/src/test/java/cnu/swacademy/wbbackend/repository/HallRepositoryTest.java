package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Hall;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HallRepositoryTest is a test class for testing the {@link HallRepository}.
 * It uses the @DataJpaTest annotation for setting up an embedded database
 * and configuring Spring Data JPA.
 */
@DataJpaTest
class HallRepositoryTest {


    @Autowired
    private HallRepository hallRepository;

    /**
     * Tests the findByName method in {@link HallRepository}.
     * It checks if the method returns the correct Hall entity based on the given hall name.
     */
    @Test
    void findByName() {
        // Given
        String hallName = "Test Hall";
        Hall hall = new Hall();
        hall.setName(hallName);
        hallRepository.save(hall);

        // When
        Optional<Hall> foundHall = hallRepository.findByName(hallName);

        // Then
        assertThat(foundHall).isPresent();
        assertThat(foundHall.get().getName()).isEqualTo(hallName);
    }
}
