package cnu.swacademy.wbbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Seat is an entity class representing a seat in a hall.
 * It is mapped to the "seat" table in the database.
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name = "seat")
public class Seat {

    /**
     * The unique identifier for the seat.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the seat.
     */
    @Column(name = "name")
    private String seatName;

    /**
     * The list of reviews associated with this seat.
     * Mapped to the "seat" field in the Review entity.
     */
    @OneToMany(mappedBy = "seat", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    /**
     * The hall this seat is associated with.
     * Mapped to the "seatList" field in the Hall entity.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false) // Hall 이 지정되어 있지 않은 Seat 레코드는 존재 불가
    private Hall hall;

    /**
     * Convenience method to set the hall for this seat and update the relationship between
     * the seat and the hall.
     *
     * @param hall the hall to be associated with this seat.
     */
    public void setHall(Hall hall) {
        this.hall = hall;
        hall.getSeatList().add(this);
    }
}
