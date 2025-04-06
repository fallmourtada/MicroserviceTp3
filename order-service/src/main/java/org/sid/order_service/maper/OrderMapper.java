package org.sid.order_service.maper;

import org.sid.order_service.dto.OrderDTO;
import org.sid.order_service.entites.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order fromOrderDTO(OrderDTO orderDTO){
        if (orderDTO == null)
            return null;
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return order;
    }

    public OrderDTO fromOrder(Order order){
        if (order == null)
            return null;
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }
}