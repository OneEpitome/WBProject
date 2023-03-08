package cnu.swacademy.wbbackend.domain.review;

import cnu.swacademy.wbbackend.domain.member.Member;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    private Member owner;

    private LocalDateTime createdAt;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private Integer likes;
}
