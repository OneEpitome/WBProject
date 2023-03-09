package cnu.swacademy.wbbackend.domain.seat;

import cnu.swacademy.wbbackend.domain.hall.Hall;
import cnu.swacademy.wbbackend.domain.review.Review;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seat")
public class Seat {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @OneToMany
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne
    private Hall selectedHall;
}
