package com.example.costume_rental;

import com.example.costume_rental.model.CostumeReturnDetail;
import com.example.costume_rental.model.Penalty;
import com.example.costume_rental.repository.PenaltyRepository;
import com.example.costume_rental.service.PenaltyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class PenaltyServiceTest {
    @Autowired
    PenaltyService penaltyService;
    @Autowired
    private PenaltyRepository penaltyRepository;

    @Test
    @Transactional
    @Rollback
    void saveTest(){
        CostumeReturnDetail costumeReturnDetail = new CostumeReturnDetail();
        costumeReturnDetail.setBorrowedDays(4);
        costumeReturnDetail.setQuantityReturn(2);
        Penalty penalty = new Penalty();
        penalty.setFine(20000.0);
        penalty.setStatus("Dat ban");
        penalty.setCostumeReturnDetail(costumeReturnDetail);
        penaltyService.save(penalty);
    }
}
