package com.example.costume_rental.service;

import com.example.costume_rental.dto.CustomerDTO;
import com.example.costume_rental.model.Customer;
import com.example.costume_rental.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public List<CustomerDTO> findListCustomers(String name){
        List<Customer> listCustomer = customerRepository.findAllCustomerByNameContains(name);
        List<CustomerDTO> list = new ArrayList<>();
        listCustomer.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer, customerDTO);
            list.add(customerDTO);
        });
        return list;
    }
    public Customer getCustomer(Integer id){
        Customer customer = customerRepository.findCustomerById(id);
        return customer;
    }
}
