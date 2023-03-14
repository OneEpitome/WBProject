package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberService;
import cnu.swacademy.wbbackend.domain.review.Review;
import cnu.swacademy.wbbackend.domain.review.ReviewService;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import cnu.swacademy.wbbackend.domain.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/review")
@RestController
public class ReviewController {

    private final SeatRepository seatRepository; // seatService 로 이동 필요
    private final MemberService memberService;
    private final ReviewService reviewService;

    @PostMapping("/save")
    public Review save(@ModelAttribute Review review, @RequestParam Long seatId, @RequestParam Long memberId) {
        Seat seat = seatRepository.findById(seatId).get();
        Member member = memberService.findById(memberId).get(); // memberService 의 findById 메소드 수정 필요
        review.setSeat(seat);
        review.setWriter(member);

        return reviewService.save(review);
    }

    @GetMapping("/{reviewId}")
    public Review findById(@PathVariable Long reviewId) {
        return reviewService.findById(reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteById(@PathVariable Long reviewId) {
        reviewService.deleteById(reviewId);
    }
}
