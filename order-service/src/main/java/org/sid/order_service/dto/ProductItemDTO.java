package org.sid.order_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.sid.order_service.entites.Order;
import org.sid.order_service.modele.Product;

@Data
public class ProductItemDTO {
    private Long id;

    private Long productId;


    private Product product;

    private double price;

    private int quantity;

    private double discount;

    private OrderDTO orderDTO;

}