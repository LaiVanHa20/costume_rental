package com.example.costume_rental.service;

import com.example.costume_rental.model.Penalty;
import com.example.costume_rental.repository.PenaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PenaltyService {
    private final PenaltyRepository penaltyRepository;

    public void save(Penalty penalty){
        penaltyRepository.save(penalty);
    }
}
