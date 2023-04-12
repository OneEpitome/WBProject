package cnu.swacademy.wbbackend.dto;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.entity.Seat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private String content;
    private String filename;
    private String filepath;
    private Seat seat;
    private Member writer;
    private LocalDateTime createdAt;
}