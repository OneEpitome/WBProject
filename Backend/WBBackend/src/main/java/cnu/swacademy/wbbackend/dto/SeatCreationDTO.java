package cnu.swacademy.wbbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SeatDTO is a DTO class representing a seat in a hall.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreationDTO {

    /**
     * The name of the seat.
     */
    private String seatName;

    /**
     * The name of the hall associated with this seat.
     */
    private String hallName;
}
