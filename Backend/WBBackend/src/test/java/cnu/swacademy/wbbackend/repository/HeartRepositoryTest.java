package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Heart;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class HeartRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HeartRepository heartRepository;

    @Test
    public void findHeartByMemberIdAndReviewIdTest() {
        // Given
        Member member = new Member("testUser", "testPassword", "testNickname", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        entityManager.persist(member);

        Review review = new Review();
        review.setWriter(member);
        review.setTitle("Test Title");
        review.setContent("Test Content");
        entityManager.persist(review);

        Heart heart = new Heart();
        heart.setMember(member);
        heart.setReview(review);
        entityManager.persist(heart);

        entityManager.flush();
        entityManager.clear();

        // When
        Optional<Heart> foundHeartOptional = heartRepository.findHeartByMemberIdAndReviewId(member.getId(), review.getId());

        // Then
        assertThat(foundHeartOptional).isNotEmpty();
        Heart foundHeart = foundHeartOptional.get();
        assertThat(foundHeart.getMemberId()).isEqualTo(member.getId());
        assertThat(foundHeart.getReviewId()).isEqualTo(review.getId());
    }
}
