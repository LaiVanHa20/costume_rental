package com.example.costume_rental.service;

import com.example.costume_rental.model.CostumeReturnDetail;
import com.example.costume_rental.repository.CostumeReturnDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumeReturnDetailService {
    private final CostumeReturnDetailRepository costumeReturnDetailRepository;

    public void save(CostumeReturnDetail costumeReturnDetail){
        costumeReturnDetailRepository.save(costumeReturnDetail);
    }
}
