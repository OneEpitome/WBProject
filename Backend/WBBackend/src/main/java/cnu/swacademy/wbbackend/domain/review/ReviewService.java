package cnu.swacademy.wbbackend.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review save(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review ID : " + reviewId + " is Not Found"));
    }

    // public Review update(Review review);

    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    //요고 일모에게 물어봐야겠따
    public void uplodeFile(Review review, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files"; // 저장 경로 지정
        UUID uuid = UUID.randomUUID(); // 식별자 생성
        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath,fileName); // 빈 껍데기 생성 (경로, 이름)
        file.transferTo(saveFile);

        //경로랑 이름넣기
        review.setFilename(fileName);
        review.setFilepath("/files/"+fileName);

        reviewRepository.save(review);
    }
}
