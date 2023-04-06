package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.service.HeartService;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;
    private final MemberService memberService;
    private final ReviewService reviewService;

    // 눌렀으면 true, 안눌렀으면 false 반환
    @GetMapping
    public ResponseEntity<Object> checkHeart(@RequestParam Long memberId, @RequestParam Long reviewId) {

//        SecurityContextHolder.getContext().getAuthentication().getName()
        Member member = memberService.findById(memberId).get();
        Review review = reviewService.findById(reviewId);

        Map<String, Boolean> res = new HashMap<>();
        res.put("status", heartService.readHeart(member, review));

        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public ResponseEntity<Object> insertOrDeleteHeart(@RequestParam Long memberId, @RequestParam Long reviewId) {

//        SecurityContextHolder.getContext().getAuthentication().getName()
        Member member = memberService.findById(memberId).get();
        Review review = reviewService.findById(reviewId);

        Map<String, Boolean> res = new HashMap<>();
        res.put("status", heartService.heartOrDelete(member, review));

        return ResponseEntity.ok().body(res);
    }

}
