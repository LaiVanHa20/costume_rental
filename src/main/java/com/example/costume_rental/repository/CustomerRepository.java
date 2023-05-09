package com.example.costume_rental.repository;

import com.example.costume_rental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllCustomerByNameContains(String name);
    Customer findCustomerById(Integer id);
}
