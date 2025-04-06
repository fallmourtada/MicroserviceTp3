package org.sid.inventory_service.mapper;


import org.sid.inventory_service.dto.ProductDTO;
import org.sid.inventory_service.entites.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service

public class ProductMapper {

  public Product fromProductDTO(ProductDTO productDTO){
      if(productDTO == null){
          return null;
      }

      Product product = new Product();
      BeanUtils.copyProperties(productDTO,product);
      return product;
  }

    public ProductDTO fromProduct(Product product){
        if(product == null){
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product,productDTO);
        return productDTO;
    }
}
