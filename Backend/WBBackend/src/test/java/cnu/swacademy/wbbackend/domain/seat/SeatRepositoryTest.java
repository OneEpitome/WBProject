package cnu.swacademy.wbbackend.domain.seat;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.service.HallService;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.repository.SeatRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SeatRepositoryTest {

    private static String hallName = "Hanhwa-Eagles Park";
    @Autowired
    HallRepository hallRepository;

    @Autowired
    HallService hallService;

    @Autowired
    SeatRepository seatRepository;

    @BeforeEach
    void setup() {
        Hall hall = new Hall();
        hall.setName(hallName);
        hallService.save(hall);
    }

    @AfterEach
    void cleanup() {
        hallRepository.deleteAll();
        seatRepository.deleteAll();
    }

    @Test
    @DisplayName("SeatRepository 를 통한 Seat Entity 저장")
    void save() {
        //given
        Seat seat = new Seat();

        Hall hall = hallService.findByName(hallName);
        seat.setHall(hall);

        //when
        seatRepository.save(seat);

        //then
        Hall findByName = hallService.findByName(hallName);
        assertThat(seatRepository.count()).isEqualTo(1L);
        assertThat(seat.getHall()).isEqualTo(findByName);
    }
}
