package cnu.swacademy.wbbackend.domain.seat;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name = "seat")
public class Seat {
    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "seat", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false) // Hall 이 지정되어 있지 않은 Seat 레코드는 존재 불가
    private Hall hall;

    // 양방향 매핑을 위한 setHall 편의 메소드.
    public void setHall(Hall hall) {
        this.hall = hall;
        hall.getSeatList().add(this);
    }
}
