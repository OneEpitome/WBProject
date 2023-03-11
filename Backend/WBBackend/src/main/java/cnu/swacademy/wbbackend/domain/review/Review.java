package cnu.swacademy.wbbackend.domain.review;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    private Member writer;

    private LocalDateTime createdAt;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private Integer like;

    @ManyToOne
    private Seat seat;
}
