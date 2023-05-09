package com.example.costume_rental;

import com.example.costume_rental.model.CostumeReturnDetail;
import com.example.costume_rental.service.CostumeReturnDetailService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class CostumeReturnDetailServiceTest {
    @Autowired
    CostumeReturnDetailService costumeReturnDetailService;

    @Test
    @Transactional
    @Rollback
    void saveTest(){
        CostumeReturnDetail costumeReturnDetail = new CostumeReturnDetail();
        costumeReturnDetail.setQuantityReturn(2);
        costumeReturnDetail.setBorrowedDays(4);
        costumeReturnDetail.setNote("Ghi chu");
        costumeReturnDetailService.save(costumeReturnDetail);
    }
}
