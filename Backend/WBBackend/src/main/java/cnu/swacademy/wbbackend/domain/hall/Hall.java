package cnu.swacademy.wbbackend.domain.hall;

import cnu.swacademy.wbbackend.domain.seat.Seat;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode
@Table(name="hall")
public class Hall {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "hall", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Seat> seatList = new ArrayList<>();
}
