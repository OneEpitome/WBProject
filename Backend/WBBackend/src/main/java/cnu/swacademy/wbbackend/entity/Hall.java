package cnu.swacademy.wbbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Hall is an entity class representing a hall containing seats.
 * It is mapped to the "hall" table in the database.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "hall")
public class Hall {

    /**
     * The unique identifier for the hall.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the hall.
     */
    @Column(name = "name")
    private String name;

    /**
     * The list of seats associated with this hall.
     * Mapped to the "hall" field in the Seat entity.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "hall", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seatList = new ArrayList<>();

    /**
     * Adds a seat to the hall and sets the relationship between the seat and the hall.
     *
     * @param seat the seat to be added to the hall.
     */
    public void addSeat(Seat seat) {
        seatList.add(seat);
        seat.setHall(this);
    }

    /**
     * Removes a seat from the hall and breaks the relationship between the seat and the hall.
     *
     * @param seat the seat to be removed from the hall.
     */
    public void removeSeat(Seat seat) {
        seatList.remove(seat);
        seat.setHall(null);
    }
}
