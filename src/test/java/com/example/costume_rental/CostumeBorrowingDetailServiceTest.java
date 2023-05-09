package com.example.costume_rental;

import com.example.costume_rental.dto.CostumeDTO;
import com.example.costume_rental.service.CostumeBorrowingDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CostumeBorrowingDetailServiceTest {
    @Autowired
    CostumeBorrowingDetailService service;
    @Test
    void getListCostumesTest1(){
        Integer id = 1;
        List<CostumeDTO> expected = new ArrayList<>();
        expected.add(CostumeDTO.builder()
                .customerId(1).customerName("laivanha")
                .costumeId(1).costumeName("vaycotruyen").
                costumePrice(500000.0).rentCost(100000.0)
                .quantity(2).build());
        expected.add(CostumeDTO.builder()
                .customerId(1).customerName("laivanha")
                .costumeId(2).costumeName("damdahoi").
                costumePrice(1000000.0).rentCost(300000.0)
                .quantity(1).build());
        List<CostumeDTO> actual = service.getListCostumes(id);
        boolean condition = true;
        for(int i=0;i<expected.size();i++){
            CostumeDTO e = expected.get(i);
            CostumeDTO a = actual.get(i);
            if( e.getCostumeId() != a.getCostumeId() || !e.getCustomerName().equals(a.getCustomerName()) || !e.getCostumeId().equals(a.getCostumeId())
                    || !e.getCostumeName().equals(a.getCostumeName()) || !e.getCostumePrice().equals(a.getCostumePrice())
                    || !e.getRentCost().equals(a.getRentCost()) || !e.getQuantity().equals(a.getQuantity()))
                condition = false;
        }
        assertTrue(expected.size() == actual.size());
        assertTrue(condition == true);
    }
    @Test
    void getListCostumesTest2() {
        Integer id = 1123;
        List<CostumeDTO> actual = service.getListCostumes(id);
        assertTrue(actual.size()==0);
    }
}
