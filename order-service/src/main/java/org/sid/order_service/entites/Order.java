package org.sid.order_service.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.order_service.enumeration.OrderStatus;
import org.sid.order_service.modele.Customer;

import java.util.Date;
import java.util.List;
@Entity @Data @AllArgsConstructor
@NoArgsConstructor
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    private Long customerId;

    @Transient
    private Customer customer;


    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
