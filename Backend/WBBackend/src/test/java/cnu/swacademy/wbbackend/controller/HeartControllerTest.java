package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.repository.HeartRepository;
import cnu.swacademy.wbbackend.service.HeartService;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import cnu.swacademy.wbbackend.service.ReviewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class HeartControllerTest {

    @Autowired
    HeartService heartService;
    @Autowired
    HeartRepository heartRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void cleanup() {
        heartRepository.deleteAll();
        memberRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    void checkHeart() throws Exception {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));
        heartService.heartOrDelete(member, review);

        //when
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("memberId", member.getId().toString());
        params.add("reviewId", review.getId().toString());

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/recommendations").params(params))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(true));
    }

    @Test
    void insertHeart() throws Exception {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));

        //when
        //then
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("memberId", member.getId().toString());
        params.add("reviewId", review.getId().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/recommendations").params(params))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(true));
        Assertions.assertThat(reviewRepository.findById(review.getId()).get().getHeart_count()).isEqualTo(1L);
    }

    @Test
    void deleteHeart() throws Exception {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));
        heartService.heartOrDelete(member, review);

        //when
        //then
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("memberId", member.getId().toString());
        params.add("reviewId", review.getId().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/recommendations").params(params))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(false));
        Assertions.assertThat(reviewRepository.findById(review.getId()).get().getHeart_count()).isEqualTo(0L);
    }

}
