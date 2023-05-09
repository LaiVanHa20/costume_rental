package com.example.costume_rental;

import com.example.costume_rental.model.CostumeReturnDetail;
import com.example.costume_rental.model.Invoice;
import com.example.costume_rental.service.InvoiceService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class InvoiceServiceTest {
    @Autowired
    InvoiceService invoiceService;

    @Test
    @Transactional
    @Rollback
    void saveTest(){
        List<CostumeReturnDetail> costumeReturnDetailList = new ArrayList<>();
        CostumeReturnDetail costumeReturnDetail = new CostumeReturnDetail();
        costumeReturnDetail.setQuantityReturn(2);
        costumeReturnDetail.setBorrowedDays(4);
        costumeReturnDetail.setNote("Ghi chu");

        CostumeReturnDetail costumeReturnDetail1 = new CostumeReturnDetail();
        costumeReturnDetail1.setQuantityReturn(1);
        costumeReturnDetail1.setBorrowedDays(2);
        costumeReturnDetail1.setNote("Ghi chu");
        costumeReturnDetailList.add(costumeReturnDetail);
        costumeReturnDetailList.add(costumeReturnDetail1);

        Invoice invoice = new Invoice();
        invoice.setTime(new Date());
        invoice.setCostumeReturnDetails(costumeReturnDetailList);
        invoice.setTotalMoney(2000000.0);
        invoiceService.save(invoice);
    }
}
