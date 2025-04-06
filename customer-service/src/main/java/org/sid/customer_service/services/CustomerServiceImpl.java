package org.sid.customer_service.services;

import jakarta.transaction.Transactional;
import org.sid.customer_service.dto.CustomerDTO;
import org.sid.customer_service.dto.SaveCustomerDTO;
import org.sid.customer_service.entites.Customer;
import org.sid.customer_service.exception.CustomerNotFoundException;
import org.sid.customer_service.mapper.CustomerMapper;
import org.sid.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional

public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public CustomerDTO saveCustomer(SaveCustomerDTO saveCustomerDTO) {
        Customer customer = new Customer();
        customer.setName(saveCustomerDTO.getName());
        customer.setEmail(saveCustomerDTO.getEmail());
        Customer customer1 = customerRepository.save(customer);
        return customerMapper.fromCustomer(customer1);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerResponseDTOList = customerList.stream().map(customer -> customerMapper.fromCustomer(customer))
                .collect(Collectors.toList());

        return customerResponseDTOList;
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, SaveCustomerDTO saveCustomerDTO) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        customer.setName(saveCustomerDTO.getName());
        customer.setEmail(saveCustomerDTO.getEmail());
        Customer customer1 = customerRepository.save(customer);
        return customerMapper.fromCustomer(customer1);
    }

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        customerRepository.delete(customer);
    }
}
