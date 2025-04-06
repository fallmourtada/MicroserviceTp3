package org.sid.order_service.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.order_service.dto.ProductItemDTO;
import org.sid.order_service.dto.SaveProductItemDTO;
import org.sid.order_service.entites.Order;
import org.sid.order_service.entites.ProductItem;
import org.sid.order_service.exception.OrderNotFoundException;
import org.sid.order_service.exception.ProductItemNotFound;
import org.sid.order_service.maper.ProductItemMapper;
import org.sid.order_service.modele.Product;
import org.sid.order_service.openfeign.InventoryRestClient;
import org.sid.order_service.repositories.OrderRepository;
import org.sid.order_service.repositories.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class ProductItemServiceImpl implements ProductItemService {
    private ProductItemRepository productItemRepository;
    private ProductItemMapper productItemMapper;
    private InventoryRestClient inventoryRestClient;
    private OrderRepository orderRepository;

    @Override
    public ProductItemDTO createProductItem(SaveProductItemDTO saveProductItemDTO) throws ProductItemNotFound, OrderNotFoundException {
        Product product;
        try{
            product= inventoryRestClient.getProductById(saveProductItemDTO.getProductId());

            if(product==null){
                throw new ProductItemNotFound("Product not found");
            }
        }catch (Exception e){

            throw new ProductItemNotFound("ProductItem Not Found");
        }
        Long orderId = saveProductItemDTO.getOrderId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("Order Id Not Found "));

        ProductItem productItem = new ProductItem();
        productItem.setProduct(product);
        productItem.setQuantity(saveProductItemDTO.getQuantity());
        productItem.setPrice(saveProductItemDTO.getPrice());
        productItem.setDiscount(saveProductItemDTO.getDiscount());
        productItem.setProductId(saveProductItemDTO.getProductId());
        productItem.setOrder(order);
        ProductItem productItem1 = productItemRepository.save(productItem);

        return productItemMapper.fromProductItem(productItem1);
    }

    @Override
    public ProductItemDTO updateProductItem(Long productItemId, SaveProductItemDTO saveProductItemDTO) throws ProductItemNotFound {
        ProductItem productItem = productItemRepository.findById(productItemId)
                .orElseThrow(()->new ProductItemNotFound("ProductItem Not Found"));
        productItem.setQuantity(saveProductItemDTO.getQuantity());
        productItem.setPrice(saveProductItemDTO.getPrice());
        productItem.setProductId(saveProductItemDTO.getProductId());
        ProductItem productItem1 = productItemRepository.save(productItem);
        return productItemMapper.fromProductItem(productItem1);
    }

    @Override
    public ProductItemDTO getProductItem(Long productItemId) throws ProductItemNotFound {
        ProductItem productItem = productItemRepository.findById(productItemId)
                .orElseThrow(()->new ProductItemNotFound("ProductItem Not Found"));
        return productItemMapper.fromProductItem(productItem);
    }

    @Override
    public void deleteProductItem(Long productItemId) throws ProductItemNotFound {
        ProductItem productItem = productItemRepository.findById(productItemId)
                .orElseThrow(()->new ProductItemNotFound("ProductItem Not FOund"));
        productItemRepository.delete(productItem);

    }

    @Override
    public List<ProductItemDTO> getAllProductItems() {
        return List.of();
    }

    @Override
    public List<ProductItemDTO> getProductItemsByProductId(Long productId) {
        List<ProductItem> productItemList = productItemRepository.findByProductId(productId);
        List<ProductItemDTO> productItemDTOS =productItemList.stream().map(productItem -> productItemMapper.fromProductItem(productItem))
                .collect(Collectors.toList());

        return productItemDTOS;
    }
}
