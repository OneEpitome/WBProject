package cnu.swacademy.wbbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter @Setter @EqualsAndHashCode
@Entity @DynamicInsert
@Table(name = "review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
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

    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    @JsonBackReference
    @JsonIgnore // 테스트 코드에서 json 직렬화 문제 발생해서 달아줌
    @ManyToOne
    private Seat seat;

    @Column(name = "seat_id", updatable = false, insertable = false)
    private Long seatId;

    @ColumnDefault(value = "0")
    @Column(name = "heart_count")
    private Long heartCount = 0L;

    public void incHeartCount() {
        ++this.heartCount;
    }

    public void decHeartCount() {
        --this.heartCount;
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
