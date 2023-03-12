package cnu.swacademy.wbbackend.domain.hall;

import cnu.swacademy.wbbackend.domain.seat.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="hall")
public class Hall {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "hall_id")
    private Long id;

    @Column(name = "hall_name")
    private String hallName;

    @OneToMany(mappedBy = "hall")
    private List<Seat> seatList = new ArrayList<>();
}
