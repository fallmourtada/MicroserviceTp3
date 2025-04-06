package org.sid.customer_service.services;

import org.sid.customer_service.dto.CustomerDTO;
import org.sid.customer_service.dto.SaveCustomerDTO;
import org.sid.customer_service.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    public CustomerDTO saveCustomer(SaveCustomerDTO saveCustomerDTO);

    public CustomerDTO getCustomerById(Long customerId) throws CustomerNotFoundException;

    public List<CustomerDTO> getAllCustomers();

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    public CustomerDTO updateCustomer(Long customerId,SaveCustomerDTO saveCustomerDTO) throws CustomerNotFoundException;
}
