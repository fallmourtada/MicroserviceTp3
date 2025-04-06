package org.sid.customer_service.controller;

import lombok.AllArgsConstructor;
import org.sid.customer_service.dto.CustomerDTO;
import org.sid.customer_service.dto.SaveCustomerDTO;
import org.sid.customer_service.exception.CustomerNotFoundException;
import org.sid.customer_service.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class CustomerRestApi {
    private CustomerService customerService;

    @PostMapping("/customers")
    public CustomerDTO createCustomer(@RequestBody SaveCustomerDTO saveCustomerDTO) {
        return customerService.saveCustomer(saveCustomerDTO);
    }

    @GetMapping("/customers/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable Long customerId ) throws CustomerNotFoundException {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/test")
    public String testSwagger() {
        return "Swagger fonctionne !";
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody SaveCustomerDTO saveCustomerDTO) throws CustomerNotFoundException {
        return customerService.updateCustomer(customerId,saveCustomerDTO);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(customerId);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
