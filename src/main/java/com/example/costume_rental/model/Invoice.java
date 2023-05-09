package com.example.costume_rental.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblInvoice")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Double totalMoney;
    Double refunds;
    Date time;
    String descripton;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "cashierId")
    private Cashier cashier;
    @OneToMany(mappedBy = "invoice")
    private List<CostumeReturnDetail> costumeReturnDetails;
}
