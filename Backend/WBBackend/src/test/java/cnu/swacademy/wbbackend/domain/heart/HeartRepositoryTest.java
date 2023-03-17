package cnu.swacademy.wbbackend.domain.heart;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberRepository;
import cnu.swacademy.wbbackend.domain.review.Review;
import cnu.swacademy.wbbackend.domain.review.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class HeartRepositoryTest {

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @AfterEach
    void cleanup() {
        heartRepository.deleteAll();
        memberRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    void findHeartByMemberIdAndReviewId() {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Long memberId = member.getId();
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));
        Long reviewId = review.getId();

        Heart heart = new Heart();
        heart.setMember(member);
        heart.setReview(review);
        heartRepository.save(heart);

        //when
        //then
        Assertions.assertThat(heartRepository.findHeartByMemberIdAndReviewId(memberId, reviewId)).isNotEmpty();
    }
}
