package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.dto.SeatCreationDTO;
import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.service.SeatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SeatControllerTest is a test class for testing the SeatController.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(SeatController.class)
public class SeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SeatService seatService;

    private SeatCreationDTO seatDTO;
    private Seat seat;

    /**
     * Sets up the test data before each test case.
     */
    @BeforeEach
    void setUp() {
        Hall hall = new Hall();
        hall.setId(1L);
        hall.setName("Hall 1");

        seatDTO = new SeatCreationDTO("A1", "Hall 1");

        seat = new Seat();
        seat.setId(1L);
        seat.setSeatName("A1");
        seat.setHall(hall);
    }

    /**
     * Tests the createSeat method of the SeatController.
     */
    @Test
    @WithMockUser // @WithMockUser 와 csrf() 는 @WebMvcTest 가 스프링 시큐리티 설정을 제대로 못 읽기 때문에 추가함.
    void createSeat() throws Exception {
        when(seatService.save(any(SeatCreationDTO.class))).thenReturn(seat);

        mockMvc.perform(post("/api/seats").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seatDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.seatName").value("A1"))
                .andExpect(jsonPath("$.reviewList").exists());
    }
}
