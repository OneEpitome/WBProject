package cnu.swacademy.wbbackend.domain.seat;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.review.Review;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter @EqualsAndHashCode
@Entity
@Table(name = "seat")
public class Seat {

    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "seat")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;
}
