package org.sid.order_service.repositories;

import org.sid.order_service.entites.Order;
import org.sid.order_service.entites.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    List<ProductItem> findByProductId(Long productId);

    List<ProductItem> findByOrderId(Long orderId);
}