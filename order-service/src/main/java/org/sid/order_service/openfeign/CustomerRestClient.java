package org.sid.order_service.openfeign;

import org.sid.order_service.modele.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/v1/api/customers")
    public List<Customer> getAllCustomers();

    @GetMapping("/v1/api/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId);

}