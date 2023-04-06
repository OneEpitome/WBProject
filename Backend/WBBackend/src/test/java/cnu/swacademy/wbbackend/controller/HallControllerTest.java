package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.service.HallService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    @DisplayName("/api/halls 를 통해 Hall Entity 를 생성할 수 있다.")
    void createHall() throws Exception {
        //given
        String hallName = "Hanbat Baseball Stadium";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/halls").param("name", hallName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(hallName)));
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
        mockMvc.perform(MockMvcRequestBuilders.get("/api/halls/" + hallName))
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is(hallName)))
                .andExpect(MockMvcResultMatchers.jsonPath("seatList").exists());
    }
}
