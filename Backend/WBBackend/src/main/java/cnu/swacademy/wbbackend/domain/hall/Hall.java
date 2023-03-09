package cnu.swacademy.wbbackend.domain.hall;

import cnu.swacademy.wbbackend.domain.seat.Seat;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="hall")
public class Hall {

    @Id @GeneratedValue
    @Column(name = "hall_id")
    private Long id;

    @OneToMany(mappedBy = "selectedHall")
    private List<Seat> seatList = new ArrayList<>();
}
