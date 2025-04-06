package org.sid.order_service.openfeign;

import org.sid.order_service.modele.Customer;
import org.sid.order_service.modele.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



    @FeignClient(name = "inventory-service")

    public interface InventoryRestClient {

        @GetMapping("/v1/api/products")
        public List<Product> getAllProducts();

        @GetMapping("/v1/api/products/{productId}")
        public Product getProductById(@PathVariable Long productId);


}