package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.entity.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SeatRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SeatRepository seatRepository;

    @Test
    public void testSaveAndFindById() {
        // given
        Hall hall = new Hall();
        hall.setName("Hall 1");
        entityManager.persist(hall);

        Seat seat = new Seat();
        seat.setSeatName("A1");
        seat.setHall(hall);
        entityManager.persist(seat);
        entityManager.flush();

        // when
        Optional<Seat> foundSeat = seatRepository.findById(seat.getId());

        // then
        assertThat(foundSeat.isPresent()).isTrue();
        assertThat(foundSeat.get().getSeatName()).isEqualTo(seat.getSeatName());
        assertThat(foundSeat.get().getHall()).isEqualTo(hall);
    }

    @Test
    public void testUpdateSeat() {
        // given
        Hall hall = new Hall();
        hall.setName("Hall 1");
        entityManager.persist(hall);

        Seat seat = new Seat();
        seat.setSeatName("A1");
        seat.setHall(hall);
        entityManager.persist(seat);
        entityManager.flush();

        // when
        seat.setSeatName("A2");
        seatRepository.save(seat);

        // then
        Optional<Seat> updatedSeat = seatRepository.findById(seat.getId());
        assertThat(updatedSeat.isPresent()).isTrue();
        assertThat(updatedSeat.get().getSeatName()).isEqualTo("A2");
    }

    @Test
    public void testDeleteSeat() {
        // given
        Hall hall = new Hall();
        hall.setName("Hall 1");
        entityManager.persist(hall);

        Seat seat = new Seat();
        seat.setSeatName("A1");
        seat.setHall(hall);
        entityManager.persist(seat);
        entityManager.flush();

        // when
        seatRepository.deleteById(seat.getId());

        // then
        Optional<Seat> deletedSeat = seatRepository.findById(seat.getId());
        assertThat(deletedSeat.isPresent()).isFalse();
    }

    @Test
    public void testFindAll() {
        // given
        Hall hall = new Hall();
        hall.setName("Hall 1");
        entityManager.persist(hall);

        Seat seat1 = new Seat();
        seat1.setSeatName("A1");
        seat1.setHall(hall);
        entityManager.persist(seat1);

        Seat seat2 = new Seat();
        seat2.setSeatName("A2");
        seat2.setHall(hall);
        entityManager.persist(seat2);

        entityManager.flush();

        // when
        List<Seat> seats = seatRepository.findAll();

        // then
        assertThat(seats).hasSize(2);
        assertThat(seats).contains(seat1, seat2);
    }

    @Test
    public void testFindByHallNameAndSeatName() {
        // Given
        Hall hall = new Hall();
        hall.setName("Test Hall");
        entityManager.persist(hall);

        Seat seat = new Seat();
        seat.setSeatName("Test Seat");
        seat.setHall(hall);
        entityManager.persist(seat);
        entityManager.flush();

        // When
        Optional<Seat> foundSeat = seatRepository.findByHallNameAndSeatName(hall.getName(), seat.getSeatName());

        // Then
        assertThat(foundSeat.isPresent()).isTrue();
        assertThat(foundSeat.get().getSeatName()).isEqualTo(seat.getSeatName());
        assertThat(foundSeat.get().getHall().getName()).isEqualTo(hall.getName());
    }
}
