package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.hall.HallRepository;
import cnu.swacademy.wbbackend.domain.hall.HallService;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import cnu.swacademy.wbbackend.domain.seat.SeatRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SeatControllerTest {

    private static String hallName = "Hanhwa-Eagles Park";

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    HallService hallService;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void cleanup() {
        seatRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        Hall hall = new Hall();
        hall.setName(hallName);

        hallService.save(hall);
    }

    @Test
    @DisplayName("Seat Controller 를 사용하여 Seat Entity 를 추가할 수 있다.")
    void save() throws Exception {
        //given
        /*
        * setup 에 mock Hall Entity 등록됨.
        *
        * */

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/seat/create").param("hallId", "1"))
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("reviewList").exists());
        assertThat(seatRepository.count()).isEqualTo(1L);
        assertThat(seatRepository.findById(1L).get().getHall().getName()).isEqualTo(hallName);
    }
}
