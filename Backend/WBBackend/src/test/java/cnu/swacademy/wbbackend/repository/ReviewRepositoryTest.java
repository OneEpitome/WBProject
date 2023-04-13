package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.entity.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HallRepository hallRepository;

    private Seat seat;
    private Member member;

    @BeforeEach
    void setUp() {
        Hall hall = new Hall();
        hall.setName("Test Hall");
        hall = hallRepository.save(hall);

        seat = new Seat();
        seat.setSeatName("A1");
        seat.setHall(hall);
        seat = seatRepository.save(seat);

        member = new Member();
        member.setUsername("test@example.com");
        member.setPassword("testpassword");
        member.setNickname("Test User");
        member = memberRepository.save(member);
    }

    @Test
    void saveAndFindReview() {
        Review review = new Review();
        review.setContent("Test content");
        review.setWriter(member);
        review.setSeat(seat);

        review = reviewRepository.save(review);

        Review foundReview = reviewRepository.findById(review.getId()).orElse(null);

        assertThat(foundReview).isNotNull();
        assertThat(foundReview.getId()).isEqualTo(review.getId());
        assertThat(foundReview.getContent()).isEqualTo(review.getContent());
        assertThat(foundReview.getWriter().getId()).isEqualTo(member.getId());
        assertThat(foundReview.getSeat().getId()).isEqualTo(seat.getId());
    }

    @Test
    void deleteReview() {
        Review review = new Review();
        review.setContent("Test content");
        review.setWriter(member);
        review.setSeat(seat);

        review = reviewRepository.save(review);
        Long reviewId = review.getId();

        reviewRepository.deleteById(reviewId);

        Review foundReview = reviewRepository.findById(reviewId).orElse(null);

        assertThat(foundReview).isNull();
    }
}
