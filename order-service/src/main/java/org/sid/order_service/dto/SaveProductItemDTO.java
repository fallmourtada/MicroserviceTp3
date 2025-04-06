package org.sid.order_service.dto;

import lombok.Data;
import org.sid.order_service.entites.Order;
import org.sid.order_service.modele.Product;

@Data
public class SaveProductItemDTO {


    private Long productId;

    private double price;

    private int quantity;

    private double discount;

    private Long orderId;

}