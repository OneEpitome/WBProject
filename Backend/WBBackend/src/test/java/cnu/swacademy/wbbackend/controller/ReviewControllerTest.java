package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.hall.HallRepository;
import cnu.swacademy.wbbackend.domain.hall.HallService;
import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberRepository;
import cnu.swacademy.wbbackend.domain.member.MemberService;
import cnu.swacademy.wbbackend.domain.review.Review;
import cnu.swacademy.wbbackend.domain.review.ReviewRepository;
import cnu.swacademy.wbbackend.domain.review.ReviewService;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import cnu.swacademy.wbbackend.domain.seat.SeatRepository;
import cnu.swacademy.wbbackend.domain.seat.SeatService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

@AutoConfigureMockMvc
@SpringBootTest
public class ReviewControllerTest {

    @Autowired
    HallService hallService;
    @Autowired
    HallRepository hallRepository;

    @Autowired
    SeatService seatService;

    @Autowired
    SeatRepository seatRepository;

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

    @BeforeEach
    void setup() {
        Hall hall = hallService.save(new Hall());

        Seat seat = new Seat();
        seat.setHall(hall);
        seatService.save(seat);

        memberService.save(
                new Member("mockUser", "password", "nick1name", "ROLE_USER"));
    }

    @AfterEach
    void cleanup() {
        hallRepository.deleteAll();
        seatRepository.deleteAll();
        memberRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    void save() throws Exception {

        //given
        Long memberId = memberRepository.findAll().get(0).getId();
        Long seatId = seatRepository.findAll().get(0).getId();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("title", "Title1");
        params.add("content", "Content1");
        params.add("memberId", memberId.toString());
        params.add("seatId", seatId.toString());

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/review/save")
                        .params(params))
                .andExpect(MockMvcResultMatchers.jsonPath("title").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("createdAt").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("writerName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("seatId").exists());
    }

    @Test
    void findById() throws Exception {
        //given
        String title = "Title";
        String content = "Content";
        LocalDateTime now = LocalDateTime.now();
        Member member = memberRepository.findAll().get(0);
        Seat seat = seatRepository.findAll().get(0);

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setCreatedAt(now);
        review.setWriter(member);
        review.setSeat(seat);

        Review save = reviewService.save(review);

        //when
        Long reviewId = save.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/" + reviewId))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("writerName").value(member.getNickname()))
                .andExpect(MockMvcResultMatchers.jsonPath("seatId").value(seat.getId()));
    }

    @Test
    void deleteById() throws Exception {
        //given
        String title = "Title";
        String content = "Content";
        LocalDateTime now = LocalDateTime.now();
        Member member = memberRepository.findAll().get(0);
        Seat seat = seatRepository.findAll().get(0);

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setCreatedAt(now);
        review.setWriter(member);
        review.setSeat(seat);

        Review save = reviewService.save(review);

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/review/" + save.getId()));

        //then
        Assertions.assertThat(reviewRepository.count()).isEqualTo(0L);
    }
}
