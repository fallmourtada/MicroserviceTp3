package org.sid.order_service.controller;

import lombok.AllArgsConstructor;
import org.sid.order_service.dto.ProductItemDTO;
import org.sid.order_service.dto.SaveProductItemDTO;
import org.sid.order_service.exception.OrderNotFoundException;
import org.sid.order_service.exception.ProductItemNotFound;
import org.sid.order_service.services.ProductItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api/product-items")
public class ProductItemRestApi {
    private ProductItemService productItemService;

    @PostMapping("/productItems")
    public ProductItemDTO createProductItem(@RequestBody SaveProductItemDTO saveProductItemDTO) throws ProductItemNotFound, OrderNotFoundException {
        return productItemService.createProductItem(saveProductItemDTO);
    }

    @GetMapping("/productItems/{id}")
    public ProductItemDTO getProductItemById(@PathVariable Long id) throws ProductItemNotFound {
        return productItemService.getProductItem(id);
    }

    @GetMapping("/productItems")
    public List<ProductItemDTO> getAllProductItems() throws ProductItemNotFound {
        return productItemService.getAllProductItems();
    }

    @PutMapping("/productItems/{productItemId}")
    public ProductItemDTO updateProductItem(@PathVariable Long productItemId,@RequestBody SaveProductItemDTO saveProductItemDTO) throws ProductItemNotFound {
        return productItemService.updateProductItem(productItemId, saveProductItemDTO);
    }

    @DeleteMapping("/productItems/{productItemId}")
    public void deleteProductItem(@PathVariable Long productItemId) throws ProductItemNotFound {
        productItemService.deleteProductItem(productItemId);
    }

    @GetMapping("/productItems/by-product/{productId}")
    public List<ProductItemDTO> getProductItemsByProductId(@PathVariable Long productId) throws ProductItemNotFound {
        return productItemService.getProductItemsByProductId(productId);
    }
}
