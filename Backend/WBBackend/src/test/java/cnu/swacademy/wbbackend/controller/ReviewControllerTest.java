package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.dto.ReviewRequestDTO;
import cnu.swacademy.wbbackend.entity.*;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.service.ReviewService;
import cnu.swacademy.wbbackend.service.SeatService;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatService seatService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private ReviewService reviewService;

    private Review review;
    private Member member;
    private Hall hall;
    private Seat seat;
    private String testImageFilePath;

    @BeforeEach
    public void setUp() {
        member = new Member("username", "password", "nickname");
        hall = new Hall();
        hall.setName("Test Hall");

        seat = new Seat();
        seat.setSeatName("Test Seat");
        hall.addSeat(seat);

        review = new Review();
        review.setId(1L);
        review.setWriter(member);
        review.setSeat(seat);
        review.setContent("Test Review");
    }

    @AfterEach
    public void tearDown() {
        if (testImageFilePath != null) {
            String projectPath = System.getProperty("user.dir") + File.separator +
                    "src" + File.separator + "main" + File.separator + "resources" + File.separator +
                    "static" + File.separator + "images" + File.separator;
            File testImageFile = new File(projectPath + testImageFilePath);
            if (testImageFile.exists()) {
                testImageFile.delete();
            }
        }
    }

    @Test
    @WithMockUser
    public void saveTest() throws Exception {
        when(seatService.findById(1L)).thenReturn(seat);
        when(memberService.findById(1L)).thenReturn(member);
        when(reviewService.createReview(any(Review.class))).thenAnswer(invocation -> {
            Review inputReview = invocation.getArgument(0, Review.class);
            inputReview.setId(1L);
            return inputReview;
        });

        MockMultipartFile imageFile = new MockMultipartFile("imageFile", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test-image".getBytes());

        ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO();
        reviewRequestDTO.setContent(review.getContent());
        reviewRequestDTO.setSeatId(1L);
        reviewRequestDTO.setMemberId(1L);
        reviewRequestDTO.setImageFile(imageFile);

        MvcResult mvcResult = mockMvc.perform(multipart("/api/reviews")
                        .file(imageFile)
                        .param("seatId", reviewRequestDTO.getSeatId().toString())
                        .param("memberId", reviewRequestDTO.getMemberId().toString())
                        .param("content", reviewRequestDTO.getContent())
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(review.getId()))
                .andExpect(jsonPath("$.content").value(review.getContent()))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        testImageFilePath = jsonObject.getString("filename");
        System.out.println(testImageFilePath);

        verify(reviewService, times(1)).createReview(any(Review.class));
    }

    @Test
    @WithMockUser
    public void deleteByIdTest() throws Exception {
        doNothing().when(reviewService).deleteById(1L);

        mockMvc.perform(delete("/api/reviews/{reviewId}", 1L)
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(reviewService, times(1)).deleteById(1L);
    }
}
