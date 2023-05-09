package com.example.costume_rental.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tblCostumeBorrowingDetail")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class CostumeBorrowingDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer quantity;
    Double rentCost;
    String note;
    @ManyToOne
    @JoinColumn(name = "costumeId")
    private Costume costume;
    @ManyToOne
    @JoinColumn(name = "borrowingSlipId")
    private BorrowingSlip borrowingSlip;
}
