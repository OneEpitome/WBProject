package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.*;
import cnu.swacademy.wbbackend.exception.ReviewNotFoundException;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;

    private Review review;
    private Member member;
    private Hall hall;
    private Seat seat;

    @BeforeEach
    public void setUp() {
        member = new Member("username", "password", "nickname");
        hall = new Hall();
        hall.setName("Test Hall");

        seat = new Seat();
        seat.setSeatName("Test Seat");
        hall.addSeat(seat);

        review = new Review();
        review.setWriter(member);
        review.setSeat(seat);
        review.setContent("Test Review");
    }

    @Test
    public void createReviewTest() {
        when(reviewRepository.save(review)).thenReturn(review);

        Review createdReview = reviewService.createReview(review);

        assertNotNull(createdReview);
        assertEquals(createdReview.getContent(), review.getContent());
        assertNotNull(createdReview.getCreatedAt());
    }

    @Test
    public void findByIdTest() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Review foundReview = reviewService.findById(1L);

        assertNotNull(foundReview);
        assertEquals(foundReview.getContent(), review.getContent());
    }

    @Test
    public void findByIdNotFoundTest() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class, () -> reviewService.findById(1L));
    }

    @Test
    public void deleteByIdTest() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).deleteById(1L);

        reviewService.deleteById(1L);

        verify(reviewRepository, times(1)).deleteById(1L);
    }
}
