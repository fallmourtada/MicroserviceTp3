package org.sid.customer_service.mapper;

import org.sid.customer_service.dto.CustomerDTO;
import org.sid.customer_service.entites.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        if(customerDTO == null){
            return null;
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;

    }

    public CustomerDTO fromCustomer(Customer customer){
        if(customer == null){
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
}
