package cnu.swacademy.wbbackend.domain.hall;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HallServiceTest {
    private static String hallName = "Hanhwa-Eagles Park";

    @Autowired
    HallService hallService;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setup() {
        hallRepository.deleteAll();
    }
    @AfterEach
    void cleanup() {
        hallRepository.deleteAll();
    }

    @Test
    @DisplayName("HallService 를 통한 Hall 저장")
    void save() {
        //given
        String hallName = "Hanhwa-Eagles Park";

        Hall hall = new Hall();
        hall.setName(hallName);

        //when
        Hall save = hallService.save(hall);

        //then
        assertThat(save.getName()).isEqualTo(hallName);
    }

    @Test
    @DisplayName("Hall 이름으로 Hall Entity 를 조회할 수 있다.")
    void findByName() {
        //given
        Hall hall = new Hall();
        hall.setName(hallName);
        hallService.save(hall);

        //when
        Hall findByName = hallService.findByName(hallName);

        //then
        assertThat(findByName.getName()).isEqualTo(hallName);
    }
}
