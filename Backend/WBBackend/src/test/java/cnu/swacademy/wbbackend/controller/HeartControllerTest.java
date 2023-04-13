package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.service.HeartService;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeartController.class)
public class HeartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeartService heartService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private ReviewService reviewService;

    @Test
    @WithMockUser
    public void checkHeartPressedTest() throws Exception {
        Long memberId = 1L;
        Long reviewId = 1L;
        Member member = new Member("testUser", "testPassword", "testNickname");
        Review review = new Review();
        review.setId(1L);

        when(memberService.findById(memberId)).thenReturn(member);
        when(reviewService.findById(reviewId)).thenReturn(review);
        when(heartService.checkHeart(member, review)).thenReturn(true);

        mockMvc.perform(get("/api/hearts").with(csrf())
                        .param("memberId", memberId.toString())
                        .param("reviewId", reviewId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"status\":true}"));
    }

    @Test
    @WithMockUser
    public void checkHeartUnpressedTest() throws Exception {
        Long memberId = 1L;
        Long reviewId = 1L;
        Member member = new Member("testUser", "testPassword", "testNickname");
        Review review = new Review();
        review.setId(1L);

        when(memberService.findById(memberId)).thenReturn(member);
        when(reviewService.findById(reviewId)).thenReturn(review);
        when(heartService.checkHeart(member, review)).thenReturn(false);

        mockMvc.perform(get("/api/hearts").with(csrf())
                        .param("memberId", memberId.toString())
                        .param("reviewId", reviewId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"status\":false}"));
    }

    @Test
    @WithMockUser
    public void toggleHeartPressedTest() throws Exception {
        Long memberId = 1L;
        Long reviewId = 1L;
        Member member = new Member("testUser", "testPassword", "testNickname");
        Review review = new Review();
        review.setId(1L);

        when(memberService.findById(memberId)).thenReturn(member);
        when(reviewService.findById(reviewId)).thenReturn(review);
        when(heartService.toggleHeart(member, review)).thenReturn(false);

        mockMvc.perform(post("/api/hearts").with(csrf())
                        .param("memberId", memberId.toString())
                        .param("reviewId", reviewId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"status\":false}"));
    }

    @Test
    @WithMockUser
    public void toggleHeartUnpressedTest() throws Exception {
        Long memberId = 1L;
        Long reviewId = 1L;
        Member member = new Member("testUser", "testPassword", "testNickname");
        Review review = new Review();
        review.setId(1L);

        when(memberService.findById(memberId)).thenReturn(member);
        when(reviewService.findById(reviewId)).thenReturn(review);
        when(heartService.toggleHeart(member, review)).thenReturn(true);

        mockMvc.perform(post("/api/hearts").with(csrf())
                        .param("memberId", memberId.toString())
                        .param("reviewId", reviewId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"status\":true}"));
    }
}
