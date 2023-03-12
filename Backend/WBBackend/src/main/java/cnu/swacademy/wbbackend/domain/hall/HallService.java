package cnu.swacademy.wbbackend.domain.hall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HallService {
    private final HallRepository hallRepository;

    public Hall save(Hall hall) {
        return hallRepository.save(hall);
    }

    public Hall findByName(String hallName) {
        return hallRepository.findHallByName(hallName)
                .orElseThrow(() -> new RuntimeException(hallName + "is Not Found"));
    }
}