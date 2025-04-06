package org.sid.order_service.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.sid.order_service.entites.ProductItem;
import org.sid.order_service.enumeration.OrderStatus;
import org.sid.order_service.modele.Customer;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    private Date created;

    private Long customerId;


    private Customer customer;


    private OrderStatus status;

    private List<ProductItem> productItems;

}