package com.example.costume_rental.model;

import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "costumeBorrowingDetailId")
    private CostumeBorrowingDetail costumeBorrowingDetail;
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;
}
