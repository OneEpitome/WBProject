package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberService;
import cnu.swacademy.wbbackend.domain.review.Review;
import cnu.swacademy.wbbackend.domain.review.ReviewRepository;
import cnu.swacademy.wbbackend.domain.review.ReviewService;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import cnu.swacademy.wbbackend.domain.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/review")
@RestController
public class ReviewController {

    private final SeatRepository seatRepository; // seatService 로 이동 필요
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final ReviewService reviewService;

    @PostMapping("/save")
    public Review save(@ModelAttribute Review review, @RequestParam Long seatId, @RequestParam Long memberId, MultipartFile file) throws Exception {
        Seat seat = seatRepository.findById(seatId).get();
        Member member = memberService.findById(memberId).get(); // memberService 의 findById 메소드 수정 필요
        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/files"; // 저장 경로 지정

        review.setSeat(seat);
        review.setWriter(member);

        UUID uuid = UUID.randomUUID(); // 식별자 생성
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath,fileName); // 빈 껍데기 생성 (경로, 이름)
        file.transferTo(saveFile);

        //경로랑 이름넣기
        review.setFilename(fileName);
        review.setFilepath("/files/"+fileName);

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
