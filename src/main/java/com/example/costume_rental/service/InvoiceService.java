package com.example.costume_rental.service;

import com.example.costume_rental.model.Invoice;
import com.example.costume_rental.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {
private final InvoiceRepository invoiceRepository;
    public void save(Invoice invoice){
        invoiceRepository.save(invoice);
    }
}
