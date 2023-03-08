package cnu.swacademy.wbbackend.domain.hall;

import cnu.swacademy.wbbackend.domain.seat.Seat;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hall {

    @Id @GeneratedValue
    @Column(name = "hall_id")
    private Long id;

    @OneToMany
    private List<Seat> seatList = new ArrayList<>();
}
