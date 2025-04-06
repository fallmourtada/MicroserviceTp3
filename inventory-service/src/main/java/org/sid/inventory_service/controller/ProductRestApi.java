package org.sid.inventory_service.controller;

import lombok.AllArgsConstructor;
import org.sid.inventory_service.dto.ProductDTO;
import org.sid.inventory_service.dto.SaveProductDTO;
import org.sid.inventory_service.exception.ProductNotFoundException;
import org.sid.inventory_service.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class ProductRestApi {
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ProductDTO getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        return productService.getProduct(productId);
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }



    @DeleteMapping("/products/{productId}")
    public void deleteProductById(@PathVariable Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody SaveProductDTO saveProductDTO) throws ProductNotFoundException {
        return productService.createProduct(saveProductDTO);
    }


    @PutMapping("/products/{productId}")
    public ProductDTO updateProduct(@PathVariable Long productId,@RequestBody SaveProductDTO saveProductDTO) throws ProductNotFoundException {
        return productService.updateProduct(productId, saveProductDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
