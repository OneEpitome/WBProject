package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Heart;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.repository.HeartRepository;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeartServiceTest {

    @InjectMocks
    private HeartService heartService;

    @Mock
    private HeartRepository heartRepository;

    @Mock
    private ReviewRepository reviewRepository;

    private Member member;
    private Review review;

    @BeforeEach
    public void setUp() {
        member = new Member("testUser", "testPassword", "testNickname", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        member.setId(1L);

        review = new Review();
        review.setWriter(member);
        review.setTitle("Test Title");
        review.setContent("Test Content");
        review.setHeart_count(0L);
        review.setId(2L);
    }

    @Test
    public void toggleHeartTest() {
        // Given
        when(heartRepository.findHeartByMemberIdAndReviewId(member.getId(), review.getId())).thenReturn(Optional.empty());
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));

        // When
        boolean heartResult = heartService.toggleHeart(member, review);

        // Then
        assertThat(heartResult).isTrue();
        assertThat(review.getHeart_count()).isEqualTo(1L);
        verify(heartRepository).save(any(Heart.class));

        // Given
        Heart heart = new Heart();
        heart.setMember(member);
        heart.setReview(review);
        when(heartRepository.findHeartByMemberIdAndReviewId(member.getId(), review.getId())).thenReturn(Optional.of(heart));

        // When
        boolean deleteResult = heartService.toggleHeart(member, review);

        // Then
        assertThat(deleteResult).isFalse();
        assertThat(review.getHeart_count()).isEqualTo(0L);
        verify(heartRepository).delete(heart);
    }

    @Test
    public void checkHeartTest() {
        // Given
        when(heartRepository.findHeartByMemberIdAndReviewId(member.getId(), review.getId())).thenReturn(Optional.empty());

        // When
        boolean notHearted = heartService.checkHeart(member, review);

        // Then
        assertThat(notHearted).isFalse();

        // Given
        Heart heart = new Heart();
        heart.setMember(member);
        heart.setReview(review);
        when(heartRepository.findHeartByMemberIdAndReviewId(member.getId(), review.getId())).thenReturn(Optional.of(heart));

        // When
        boolean hearted = heartService.checkHeart(member, review);

        // Then
        assertThat(hearted).isTrue();
    }
}
