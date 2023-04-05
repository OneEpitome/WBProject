package cnu.swacademy.wbbackend.domain.heart;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.HeartRepository;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import cnu.swacademy.wbbackend.service.HeartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HeartServiceTest {

    @Autowired
    HeartService heartService;
    @Autowired
    HeartRepository heartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @AfterEach
    void cleanup() {
        heartRepository.deleteAll();
        memberRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    void heart() {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));

        //when
        heartService.heartOrDelete(member, review);

        //then
        assertThat(reviewRepository.findById(review.getId()).get().getHeart_count()).isEqualTo(1L);
    }

    @Test
    void delete() {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));
        heartService.heartOrDelete(member, review);

        //when
        heartService.heartOrDelete(member, review);

        //then
        assertThat(reviewRepository.findById(review.getId()).get().getHeart_count()).isEqualTo(0L);
    }

    @Test
    void readHeart() {
        //given
        Member member = memberRepository.save(new Member("test", "pass", "testnick", "ROLE_USER"));
        Review review = reviewRepository.save(new Review(LocalDateTime.now(), "TITLE", "CONTENT"));
        heartService.heartOrDelete(member, review);

        //when
        //then
        assertThat(heartService.readHeart(member, review)).isEqualTo(true);
    }

}
