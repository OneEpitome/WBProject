package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.service.HallService;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import cnu.swacademy.wbbackend.repository.SeatRepository;
import cnu.swacademy.wbbackend.service.ReviewService;
import cnu.swacademy.wbbackend.service.SeatService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ReviewServiceTest {
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
    void save() {
        //given
        String title = "Title";
        String content = "Content";
        LocalDateTime now = LocalDateTime.now();

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setCreatedAt(now);

        Seat seat = seatRepository.findAll().get(0);
        review.setSeat(seat);

        Member member = memberRepository.findAll().get(0);
        review.setWriter(member);

        //when
        reviewService.save(review);

        //then
        assertThat(reviewRepository.count()).isEqualTo(1L);
    }

    @Test
    @DisplayName("ID 로 리뷰를 조회할 수 있다.")
    void findById() {
        //given
        String title = "Title";
        String content = "Content";
        LocalDateTime now = LocalDateTime.now();
        Seat seat = seatRepository.findAll().get(0);
        Member member = memberRepository.findAll().get(0);

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setSeat(seat);
        review.setWriter(member);

        Review save = reviewService.save(review);


        //when
        Long id = save.getId();
        Review findById = reviewService.findById(id);

        //then
        assertThat(findById.getTitle()).isEqualTo(title);
        assertThat(findById.getContent()).isEqualTo(content);
        assertThat(findById.getSeat()).isEqualTo(seat);
        assertThat(findById.getWriter()).isEqualTo(member);
    }

    @Test
    @DisplayName("ReviewId 로 Review Entity 를 삭제할 수 있다.")
    void deleteById() {
        //given
        Seat seat = seatRepository.findAll().get(0);
        Member member = memberRepository.findAll().get(0);

        Review review1 = new Review();
        review1.setTitle("Title1");
        review1.setContent("Content1");
        review1.setCreatedAt(LocalDateTime.now());
        review1.setSeat(seat);
        review1.setWriter(member);

        Review review2 = new Review();
        review2.setTitle("Title2");
        review2.setContent("Content2");
        review2.setCreatedAt(LocalDateTime.now());
        review2.setSeat(seat);
        review2.setWriter(member);

        Review save = reviewService.save(review1);
        reviewService.save(review2);

        //when
        Long id = save.getId();
        reviewService.deleteById(id);

        //then
        assertThat(reviewRepository.count()).isEqualTo(1L);
        Review remainingReview = reviewRepository.findAll().get(0);
        assertThat(remainingReview.getTitle()).isEqualTo("Title2");
        assertThat(remainingReview.getContent()).isEqualTo("Content2");
        assertThat(remainingReview.getSeat()).isEqualTo(seat);
        assertThat(remainingReview.getWriter()).isEqualTo(member);
    }
}
