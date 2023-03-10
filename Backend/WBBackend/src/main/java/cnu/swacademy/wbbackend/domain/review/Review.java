package cnu.swacademy.wbbackend.domain.review;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.seat.Seat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    private Member writer;

    private String writerName;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private Integer likes;

    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    @JsonBackReference
    @ManyToOne
    private Seat seat;

    @Column(name = "seat_id", updatable = false, insertable = false)
    private Long seatId;

    public Review(LocalDateTime createdAt, String title, String content) {
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
    }

    /*
    * 필수적인 메소드인지 테스트 필요
    * */
    public void setWriter(Member writer) {
        this.writer = writer;
        this.writerName = writer.getNickname();
        writer.getReviewList().add(this);
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
        seat.getReviewList().add(this);
    }
}
