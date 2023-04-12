package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.dto.ReviewRequestDTO;
import cnu.swacademy.wbbackend.dto.ReviewResponseDTO;
import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.HallRepository;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.entity.Review;
import cnu.swacademy.wbbackend.service.ReviewService;
import cnu.swacademy.wbbackend.entity.Seat;
import cnu.swacademy.wbbackend.service.SeatService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@RestController
public class ReviewController {

    private final SeatService seatService;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private static String projectPath = System.getProperty("user.dir") + File.separator +
            "src" + File.separator + "main" + File.separator + "resources" + File.separator +
            "static" + File.separator + "images" + File.separator; // 저장 경로 지정

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> save(@ModelAttribute ReviewRequestDTO reviewRequestDTO) throws Exception {

        Seat seat = seatService.findById(reviewRequestDTO.getSeatId());
        Member member = memberService.findById(reviewRequestDTO.getMemberId());

        Review review = new Review();
        review.setContent(reviewRequestDTO.getContent());
        review.setSeat(seat);
        review.setWriter(member);

        UUID uuid = UUID.randomUUID(); // 식별자 생성
        String fileName = uuid+"_"+ reviewRequestDTO.getImageFile().getOriginalFilename();
        File saveFile = new File(projectPath, fileName); // 빈 껍데기 생성 (경로, 이름)
        reviewRequestDTO.getImageFile().transferTo(saveFile);

        // 경로랑 이름 넣기
        review.setFilename(fileName);
        review.setFilepath(projectPath + fileName); // 경로 설정을 변경해줍니다.

        Review savedReview = reviewService.createReview(review);
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(
                savedReview.getId(),
                savedReview.getContent(),
                savedReview.getFilename(),
                savedReview.getFilepath(),
                savedReview.getSeat(),
                savedReview.getWriter(),
                savedReview.getCreatedAt()
        );
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable Long reviewId) {
        Review review = reviewService.findById(reviewId);
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(
                review.getId(),
                review.getContent(),
                review.getFilename(),
                review.getFilepath(),
                review.getSeat(),
                review.getWriter(),
                review.getCreatedAt()
        );
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long reviewId) {
        reviewService.deleteById(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
