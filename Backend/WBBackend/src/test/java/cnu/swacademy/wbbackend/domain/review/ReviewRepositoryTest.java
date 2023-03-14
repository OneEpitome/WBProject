package cnu.swacademy.wbbackend.domain.review;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @AfterEach
    void cleanup() {
        reviewRepository.deleteAll();
    }

    @Test
    void save() {
        //given
        String title = "title";
        String content = "content";
        LocalDateTime createdAt = LocalDateTime.now();
        Review review = new Review(createdAt, title, content);
        //when
        Review save = reviewRepository.save(review);
        //then
        assertThat(reviewRepository.findAll().get(0).getTitle()).isEqualTo(title);
        assertThat(reviewRepository.findAll().get(0).getContent()).isEqualTo(content);
        assertThat(reviewRepository.findAll().get(0).getCreatedAt()).isEqualTo(createdAt);
    }
}
