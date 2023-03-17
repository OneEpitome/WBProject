package cnu.swacademy.wbbackend.domain.heart;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.review.Review;
import cnu.swacademy.wbbackend.domain.review.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    // 좋아요 시 true 반환, 취소시 false 반환
    public boolean heartOrDelete(Member member, Review review) {

        Optional<Heart> heartByMemberIdAndReviewId = heartRepository.findHeartByMemberIdAndReviewId(
                member.getId(), review.getId());

        if (heartByMemberIdAndReviewId.isPresent()) {
            reviewRepository.findById(review.getId()).get().decHeartCount();
            heartRepository.delete(heartByMemberIdAndReviewId.get());
            return false;
        }

        reviewRepository.findById(review.getId()).get().incHeartCount();
        Heart heart = new Heart();
        heart.setMember(member);
        heart.setReview(review);
        heartRepository.save(heart);
        return true;
    }

    public boolean readHeart(Member member, Review review) {

        Optional<Heart> heartByMemberIdAndReviewId = heartRepository.findHeartByMemberIdAndReviewId(
                member.getId(), review.getId());

        if (heartByMemberIdAndReviewId.isPresent()) {
            return true;
        }

        return false;
    }
}
