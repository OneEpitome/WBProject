package cnu.swacademy.wbbackend.domain.hall;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HallRepositoryTest {

    @Autowired
    HallRepository hallRepository;

    @AfterEach
    void cleanup() {
        hallRepository.deleteAll();
    }

    @Test
    void save() {

        //given
        Hall hall = new Hall();

        //when
        hallRepository.save(hall);

        //then
        assertThat(hallRepository.count()).isEqualTo(1L);
    }
}
