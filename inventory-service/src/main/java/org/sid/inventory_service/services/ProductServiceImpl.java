package org.sid.inventory_service.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.inventory_service.dto.ProductDTO;

import org.sid.inventory_service.dto.SaveProductDTO;
import org.sid.inventory_service.entites.Product;
import org.sid.inventory_service.exception.ProductNotFoundException;
import org.sid.inventory_service.mapper.ProductMapper;
import org.sid.inventory_service.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private  ProductRepository productRepository;
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(SaveProductDTO saveProductDTO){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(saveProductDTO.getName());
        productDTO.setQuantity(saveProductDTO.getQuantity());
        productDTO.setPrice(saveProductDTO.getPrice());
        Product product = productMapper.fromProductDTO(productDTO);
        Product product1 = productRepository.save(product);
        return productMapper.fromProduct(product1);
    }

    @Override
    public ProductDTO updateProduct(Long productId, SaveProductDTO saveProductDTO) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        product.setPrice(saveProductDTO.getPrice());
        product.setName(saveProductDTO.getName());
        product.setQuantity(saveProductDTO.getQuantity());
        Product product1 = productRepository.save(product);
        return productMapper.fromProduct(product1);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
      List<Product> productList =  productRepository.findAll();
      List<ProductDTO> productResponseDTOList = productList.stream().map(product -> productMapper.fromProduct(product))
              .collect(Collectors.toList());

        return productResponseDTOList;
    }

    @Override
    public ProductDTO getProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product Not Found"));
        return productMapper.fromProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product Not Found"));

        productRepository.delete(product);

    }
}
