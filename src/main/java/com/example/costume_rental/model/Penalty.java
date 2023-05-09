package com.example.costume_rental.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tblPenalty")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Penalty {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String status;
    Double fine;
    @ManyToOne
    @JoinColumn(name = "costumeReturnDetailId")
    private CostumeReturnDetail costumeReturnDetail;
}
