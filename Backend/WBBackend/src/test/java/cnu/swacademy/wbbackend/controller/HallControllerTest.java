package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.service.HallService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The HallControllerTest class provides unit tests for the HallController class.
 */
@ExtendWith(MockitoExtension.class)
public class HallControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HallService hallService;

    @InjectMocks
    private HallController hallController;

    /**
     * Tests the createHall method in {@link HallController}.
     * It checks if the method creates a new hall and returns it with the generated ID.
     */
    @Test
    void createHall() throws Exception {
        String hallName = "Test Hall";
        Hall hall = new Hall();
        hall.setName(hallName);

        Hall savedHall = new Hall();
        savedHall.setId(1L);
        savedHall.setName(hallName);

        when(hallService.save(hall)).thenReturn(savedHall);

        mockMvc = MockMvcBuilders.standaloneSetup(hallController).build();

        mockMvc.perform(post("/api/halls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"" + hallName + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedHall.getId()))
                .andExpect(jsonPath("$.name").value(hallName));

        verify(hallService, times(1)).save(hall);
    }

    /**
     * Tests the getHall method in {@link HallController}.
     * It checks if the method retrieves the hall with the given name.
     */
    @Test
    void getHall() throws Exception {
        String hallName = "Test Hall";
        Hall hall = new Hall();
        hall.setId(1L);
        hall.setName(hallName);

        when(hallService.findByName(hallName)).thenReturn(hall);

        mockMvc = MockMvcBuilders.standaloneSetup(hallController).build();

        mockMvc.perform(get("/api/halls/{hallName}", hallName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hall.getId()))
                .andExpect(jsonPath("$.name").value(hallName));

        verify(hallService, times(1)).findByName(hallName);
    }
}
