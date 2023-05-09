package com.example.costume_rental.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {
    Integer id;
    String name;
    String email;
    String phone;
    String description;
    String address;
}
