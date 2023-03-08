package cnu.swacademy.wbbackend.domain.seat;

import cnu.swacademy.wbbackend.domain.review.Review;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Seat {

    @Id @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    @OneToMany
    private List<Review> reviewList = new ArrayList<>();
}
