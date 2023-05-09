package com.example.costume_rental.service;

import com.example.costume_rental.model.Costume;
import com.example.costume_rental.repository.CostumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumeService {
    private final CostumeRepository costumeRepository;

    public Costume findCostume(Integer id){
        return costumeRepository.findCostumeById(id);
    }
}
