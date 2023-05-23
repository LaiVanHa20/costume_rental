package com.example.costume_rental;

import com.example.costume_rental.dto.CustomerDTO;
import com.example.costume_rental.model.Customer;
import com.example.costume_rental.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;

    @Test
    void findListCustomersTest1(){
        String keyword = "ha";
        List<CustomerDTO> expected = new ArrayList<>();
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(1);
        customerDTO1.setName("laivanha");
        customerDTO1.setEmail("laivanha20@gmail.com");
        customerDTO1.setAddress("QuanHoa");
        customerDTO1.setPhone("0346323648");
        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(2);
        customerDTO2.setName("nguyenha");
        customerDTO2.setEmail("nguyenha@gmail.com");
        customerDTO2.setAddress("HoangQuocViet");
        customerDTO2.setPhone("0346323648");
        expected.add(customerDTO1);
        expected.add(customerDTO2);
        List<CustomerDTO> actual = customerService.findListCustomers(keyword);
        boolean condition = true;
        for(int i=0;i<2;i++){
            CustomerDTO e = expected.get(i);
            CustomerDTO a = actual.get(i);
            if( e.getId() != a.getId() || !e.getName().equals(a.getName()) || !e.getAddress().equals(a.getAddress())
            || !e.getPhone().equals(a.getPhone()) || !e.getEmail().equals(a.getEmail()))
                condition = false;
        }
        assertTrue(expected.size() == 2);
        assertTrue(condition == false);
    }

    @Test
    void findListCustomersTest2() {
        var ten = "afdgasdkhfiasdyf9";
        List<CustomerDTO> actual = customerService.findListCustomers(ten);
        assertTrue(actual.size()==0);
    }
    @Test
    void getCustomerTest1(){
        Customer customer = customerService.getCustomer(1);
        assertTrue(customer.getName().equals("laivanha"));
    }
    @Test
    void getCustomerTest2(){
        Customer customer = customerService.getCustomer(1219878);
        assertTrue(customer == null);
    }

}
