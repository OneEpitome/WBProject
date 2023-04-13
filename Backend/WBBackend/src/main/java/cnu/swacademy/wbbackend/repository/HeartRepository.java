package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findHeartByMemberIdAndReviewId(Long memberId, Long reviewId);
}
