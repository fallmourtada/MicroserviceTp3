package org.sid.order_service.services;

import org.sid.order_service.dto.ProductItemDTO;
import org.sid.order_service.dto.SaveProductItemDTO;
import org.sid.order_service.exception.OrderNotFoundException;
import org.sid.order_service.exception.ProductItemNotFound;

import java.util.List;

public interface ProductItemService {

    public ProductItemDTO createProductItem(SaveProductItemDTO saveProductItemDTO) throws ProductItemNotFound, OrderNotFoundException;

    public ProductItemDTO updateProductItem(Long productItemId,SaveProductItemDTO saveProductItemDTO) throws ProductItemNotFound;

    public ProductItemDTO getProductItem(Long productItemId) throws ProductItemNotFound;

    public void deleteProductItem(Long productItemId) throws ProductItemNotFound;

    public List<ProductItemDTO> getAllProductItems();

    public List<ProductItemDTO> getProductItemsByProductId(Long productId);
}
