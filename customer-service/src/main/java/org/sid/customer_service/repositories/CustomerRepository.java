package org.sid.customer_service.repositories;

import org.sid.customer_service.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
