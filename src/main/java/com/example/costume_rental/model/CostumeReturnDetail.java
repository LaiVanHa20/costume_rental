package com.example.costume_rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tblCostumeReturnDetail")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CostumeReturnDetail extends CostumeBorrowingDetail {
    Integer quantityReturn;
    Integer borrowedDays;
    String note;
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;
}
