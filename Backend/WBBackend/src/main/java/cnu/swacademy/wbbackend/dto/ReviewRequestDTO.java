package cnu.swacademy.wbbackend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReviewRequestDTO {
    private String content;
    private Long seatId;
    private Long memberId;
    private MultipartFile imageFile;
}
