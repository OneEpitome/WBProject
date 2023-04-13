package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.dto.SeatCreationDTO;
import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.exception.HallNotFoundException;
import cnu.swacademy.wbbackend.exception.SeatNotFoundException;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * SeatServiceTest is a test class for testing the {@link SeatService}.
 * It uses the @ExtendWith(MockitoExtension.class) annotation for configuring Mockito framework.
 */
@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private SeatService seatService;

    private Hall hall;

    @BeforeEach
    void setUp() {
        hall = new Hall();
        hall.setId(1L);
        hall.setName("Test Hall");
    }

    /**
     * Tests the save method in {@link SeatService}.
     * It checks if the method correctly saves a seat with the associated hall.
     */
    @Test
    void saveSeat() {
        // Given
        when(hallRepository.findByName("Test Hall")).thenReturn(Optional.of(hall));
        when(seatRepository.save(any(Seat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SeatCreationDTO seatDTO = new SeatCreationDTO();
        seatDTO.setSeatName("A1");
        seatDTO.setHallName("Test Hall");

        // When
        Seat savedSeat = seatService.save(seatDTO);

        // Then
        assertThat(savedSeat.getSeatName()).isEqualTo("A1");
        assertThat(savedSeat.getHall()).isEqualTo(hall);
    }

    /**
     * Tests the save method in {@link SeatService} when the hall is not found.
     * It checks if the method throws a HallNotFoundException.
     */
    @Test
    void saveSeatWithNonExistingHall() {
        // Given
        when(hallRepository.findByName("Non-Existing Hall")).thenReturn(Optional.empty());

        SeatCreationDTO seatDTO = new SeatCreationDTO();
        seatDTO.setSeatName("A1");
        seatDTO.setHallName("Non-Existing Hall");

        // When & Then
        assertThatThrownBy(() -> seatService.save(seatDTO))
                .isInstanceOf(HallNotFoundException.class)
                .hasMessage("Non-Existing Hall is Not Found");
    }

    @Test
    void findByHallNameAndSeatName() {
        // Given
        Seat seat = new Seat();
        seat.setSeatName("A1");
        seat.setHall(hall);

        when(seatRepository.findByHallNameAndSeatName(hall.getName(), seat.getSeatName())).thenReturn(Optional.of(seat));

        // When
        Seat foundSeat = seatService.findByHallNameAndSeatName(hall.getName(), seat.getSeatName());

        // Then
        assertThat(foundSeat).isNotNull();
        assertThat(foundSeat.getSeatName()).isEqualTo(seat.getSeatName());
        assertThat(foundSeat.getHall().getName()).isEqualTo(hall.getName());
    }

    @Test
    void findByHallNameAndSeatName_NotFound() {
        // Given
        String nonExistingHallName = "Non-Existing Hall";
        String nonExistingSeatName = "Non-Existing Seat";

        when(seatRepository.findByHallNameAndSeatName(nonExistingHallName, nonExistingSeatName)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> seatService.findByHallNameAndSeatName(nonExistingHallName, nonExistingSeatName))
                .isInstanceOf(SeatNotFoundException.class)
                .hasMessage("Seat with hall name: " + nonExistingHallName + " and seat name: " + nonExistingSeatName + " is not found");
    }

}
