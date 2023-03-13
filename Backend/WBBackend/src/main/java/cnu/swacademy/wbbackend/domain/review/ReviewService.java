package cnu.swacademy.wbbackend.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review ID : " + reviewId + " is Not Found"));
    }

    // public Review update(Review review);

    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
