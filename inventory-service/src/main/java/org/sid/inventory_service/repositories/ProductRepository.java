package org.sid.inventory_service.repositories;

import org.sid.inventory_service.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
