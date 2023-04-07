package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.repository.ReviewRepository;
import cnu.swacademy.wbbackend.service.ReviewService;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@RestController
public class ReviewController {

    private final SeatRepository seatRepository; // seatService 로 이동 필요
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private static String projectPath = System.getProperty("user.dir") + File.separator +
            "src" + File.separator + "main" + File.separator + "resources" + File.separator +
            "static" + File.separator + "images"; // 저장 경로 지정

    @PostMapping
    public Review save(@ModelAttribute Review review, @RequestParam Long seatId,
                       @RequestParam Long memberId, @RequestParam(required = false) MultipartFile imageFile) throws Exception {
        Seat seat = seatRepository.findById(seatId).get();
        Member member = memberService.findById(memberId).get(); // memberService 의 findById 메소드 수정 필요

        review.setSeat(seat);
        review.setWriter(member);

        UUID uuid = UUID.randomUUID(); // 식별자 생성
        String fileName = uuid+"_"+ imageFile.getOriginalFilename();
        File saveFile = new File(projectPath,fileName); // 빈 껍데기 생성 (경로, 이름)
        imageFile.transferTo(saveFile);

        //경로랑 이름넣기
        review.setFilename(fileName);
        review.setFilepath("/files/"+fileName);

        return reviewService.createReview(review);
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
