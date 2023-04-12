package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.exception.ReviewNotFoundException;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review ID : " + reviewId + " is Not Found"));
    }

    // public Review update(Review review);

    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
