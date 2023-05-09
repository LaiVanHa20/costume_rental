package com.example.costume_rental.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tblCostume")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Costume {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String type;
    Integer quantity;
    Double price;
    String description;
}
