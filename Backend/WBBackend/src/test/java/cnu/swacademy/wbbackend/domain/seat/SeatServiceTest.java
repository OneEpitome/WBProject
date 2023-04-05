package cnu.swacademy.wbbackend.domain.seat;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.service.HallService;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.repository.SeatRepository;
import cnu.swacademy.wbbackend.service.SeatService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SeatServiceTest {

    @Autowired
    SeatService seatService;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    HallService hallService;
    @Autowired
    HallRepository hallRepository;

    private static String hallName = "Hanhwa-Eagles Park";

    @BeforeEach
    void setup() {
        Hall hall = new Hall();
        hall.setName(hallName);
        hallService.save(hall);
    }

    @AfterEach
    void cleanup() {
        seatRepository.deleteAll();
        hallRepository.deleteAll();
    }

    @Test
    @DisplayName("Hall 이 존재할 때, Hall 을 지정하여 Seat 을 저장할 수 있다.")
    void save() {
        //given
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();

        Hall hall = hallService.findByName(hallName);
        seat1.setHall(hall);
        seat2.setHall(hall);

        //when
        seatService.save(seat1);
        seatService.save(seat2);

        //then
        Hall findByName = hallService.findByName(hallName);
        assertThat(seatRepository.count()).isEqualTo(2L);
        assertThat(findByName.getSeatList()).containsExactlyInAnyOrder(seat1, seat2);
    }
}