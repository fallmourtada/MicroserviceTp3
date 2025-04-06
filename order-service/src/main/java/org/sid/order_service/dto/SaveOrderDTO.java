package org.sid.order_service.dto;

import lombok.Data;
import org.sid.order_service.enumeration.OrderStatus;
import org.sid.order_service.modele.Customer;

import java.util.Date;

@Data
public class SaveOrderDTO {
    private Date created;

    private Long customerId;


    private OrderStatus status;

}