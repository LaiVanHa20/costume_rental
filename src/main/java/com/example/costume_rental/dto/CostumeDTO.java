package com.example.costume_rental.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Builder
public class CostumeDTO {
    String customerName;
    Integer customerId;
    Integer costumeId;
    Integer costumeBorrowingDetailId;
    String costumeName;
    String costumeType;
    Double costumePrice;
    Date borrowedDate;
    Double rentCost;
    Integer borrowedDays;
    Double refunds;
    Double totalDeposit;
    Integer quantity;
    String status;
    Double fine;
}
