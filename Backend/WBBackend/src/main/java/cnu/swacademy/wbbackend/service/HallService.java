package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Hall;
import cnu.swacademy.wbbackend.repository.HallRepository;
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
                .orElseThrow(() -> new RuntimeException(hallName + " is Not Found"));
    }

    public Hall findById(Long hallId) {
        return hallRepository.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall ID : " + hallId + " is Not Found"));
    }
}