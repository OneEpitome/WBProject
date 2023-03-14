package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.hall.HallRepository;
import cnu.swacademy.wbbackend.domain.hall.HallService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
public class HallControllerTest {

    private static final String hallName = "Hanhwa-Eagles Park";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    HallService hallService;

    @Autowired
    HallRepository hallRepository;

    @BeforeEach
    void setup() {
        Hall hall = new Hall();
        hall.setName(hallName);

        hallService.save(hall);
    }

    @AfterEach
    void cleanup() {
        hallRepository.deleteAll();
    }


    @Test
    @DisplayName("/api/hall/create 를 통해 Hall Entity 를 생성할 수 있다.")
    void save() throws Exception {
        //given
        String hallName = "Hanbat Baseball Stadium";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/hall/create").param("name", hallName));

        //then
        assertThat(hallRepository.count()).isEqualTo(2L);
        assertThat(hallService.findByName(hallName).getName()).isEqualTo(hallName);
    }

    @Test
    @DisplayName("/api/hall/{hallName} 으로 Hall Entity 를 조회할 수 있다.")
    void findByName() throws Exception {
        //given
        /*
        * setup 으로 대체, hallName : Hanhwa-Eagles Park
        * */

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hall/" + hallName))
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("seatList").exists());
    }
}
