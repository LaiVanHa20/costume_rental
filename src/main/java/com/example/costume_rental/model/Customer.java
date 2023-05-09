package com.example.costume_rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tblCustomer")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends User{
    String code;
}
