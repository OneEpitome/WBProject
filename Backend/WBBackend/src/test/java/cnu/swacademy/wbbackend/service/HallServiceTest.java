package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.exception.HallNotFoundException;
import cnu.swacademy.wbbackend.repository.HallRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * HallServiceTest is a test class for testing the {@link HallService}.
 * It uses the Mockito framework for mocking dependencies and isolating the class under test.
 */
@ExtendWith(MockitoExtension.class)
class HallServiceTest {

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallService hallService;

    /**
     * Tests the save method in {@link HallService}.
     * It checks if the method saves the hall and returns it with the generated ID.
     */
    @Test
    void save() {
        // Given
        Hall hall = new Hall();
        hall.setName("Test Hall");

        Hall savedHall = new Hall();
        savedHall.setId(1L);
        savedHall.setName("Test Hall");

        when(hallRepository.save(any(Hall.class))).thenReturn(savedHall);

        // When
        Hall result = hallService.save(hall);

        // Then
        assertThat(result).isEqualTo(savedHall);
        verify(hallRepository, times(1)).save(hall);
    }

    /**
     * Tests the findByName method in {@link HallService}.
     * It checks if the method finds the hall with the given name and throws HallNotFoundException when the hall is not found.
     */
    @Test
    void findByName() {
        // Given
        String hallName = "Test Hall";
        Hall hall = new Hall();
        hall.setId(1L);
        hall.setName(hallName);

        when(hallRepository.findByName(hallName)).thenReturn(Optional.of(hall));

        // When
        Hall foundHall = hallService.findByName(hallName);

        // Then
        assertThat(foundHall).isEqualTo(hall);
        verify(hallRepository, times(1)).findByName(hallName);

        // Test not found case
        when(hallRepository.findByName("Nonexistent Hall")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> hallService.findByName("Nonexistent Hall"))
                .isInstanceOf(HallNotFoundException.class);
    }

    /**
     * Tests the findById method in {@link HallService}.
     * It checks if the method finds the hall with the given ID and throws HallNotFoundException when the hall is not found.
     */
    @Test
    void findById() {
        // Given
        Long hallId = 1L;
        Hall hall = new Hall();
        hall.setId(hallId);
        hall.setName("Test Hall");

        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hall));

        // When
        Hall foundHall = hallService.findById(hallId);

        // Then
        assertThat(foundHall).isEqualTo(hall);
        verify(hallRepository, times(1)).findById(hallId);

        // Test not found case
        when(hallRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> hallService.findById(999L))
                .isInstanceOf(HallNotFoundException.class);
    }
}
