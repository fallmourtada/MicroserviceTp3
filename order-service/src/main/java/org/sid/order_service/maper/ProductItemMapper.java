package org.sid.order_service.maper;

import lombok.AllArgsConstructor;
import org.sid.order_service.dto.ProductItemDTO;
import org.sid.order_service.entites.ProductItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductItemMapper {
    private OrderMapper orderMapper;
    public ProductItemDTO fromProductItem(ProductItem productItem) {
        if (productItem == null) return null;
        ProductItemDTO productItemDTO = new ProductItemDTO();
        BeanUtils.copyProperties(productItem, productItemDTO);
        productItemDTO.setOrderDTO(orderMapper.fromOrder(productItem.getOrder()));
        return productItemDTO;
    }

    public ProductItem fromProductItemDTO(ProductItemDTO productItemDTO) {
        if (productItemDTO == null) return null;
        ProductItem productItem = new ProductItem();
        BeanUtils.copyProperties(productItemDTO, productItem);
        productItem.setOrder(orderMapper.fromOrderDTO(productItemDTO.getOrderDTO()));
        return productItem;
    }
}
