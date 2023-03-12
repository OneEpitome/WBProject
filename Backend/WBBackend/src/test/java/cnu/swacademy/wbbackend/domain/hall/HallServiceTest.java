package cnu.swacademy.wbbackend.domain.hall;

import cnu.swacademy.wbbackend.domain.review.Review;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HallServiceTest {

    @Autowired
    HallService hallService;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    EntityManager entityManager;

    @AfterEach
    void cleanup() {
        hallRepository.deleteAll();
    }

    @Test
    @DisplayName("HallService 를 통한 Hall 저장")
    void save() {
        //given
        String hallName = "Hanhwa-Eagles Park";
        List<Seat> seatList = List.of(new Seat(), new Seat());

        Hall hall = new Hall();
        hall.setName(hallName);
        hall.setSeatList(seatList);

        //when
        Hall save = hallService.save(hall);

        //then
        assertThat(save.getName()).isEqualTo(hallName);
        assertThat(save.getSeatList()).containsAll(seatList);
    }

    @Test
    @DisplayName("Hall 이름으로 Hall Entity 를 조회할 수 있다.")
    void findByName() {
        //given
        String hallName = "Hanhwa-Eagles Park";

        Hall hall = new Hall();
        hall.setName(hallName);
        hallService.save(hall);

        //when
        Hall findByName = hallService.findByName(hallName);

        //then
        assertThat(findByName.getName()).isEqualTo(hallName);
    }
}
