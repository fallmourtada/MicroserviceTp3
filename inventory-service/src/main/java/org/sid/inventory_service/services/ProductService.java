package org.sid.inventory_service.services;

import org.sid.inventory_service.dto.ProductDTO;

import org.sid.inventory_service.dto.SaveProductDTO;
import org.sid.inventory_service.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(SaveProductDTO saveProductDTO);

    public ProductDTO updateProduct(Long productId,SaveProductDTO saveProductDTO) throws ProductNotFoundException;

    public List<ProductDTO> getAllProducts();

    public ProductDTO getProduct(Long productId) throws ProductNotFoundException;

    public void deleteProduct(Long productId) throws ProductNotFoundException;
}
