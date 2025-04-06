package org.sid.order_service.services;

import org.sid.order_service.dto.OrderDTO;
import org.sid.order_service.dto.SaveOrderDTO;
import org.sid.order_service.exception.CustomerNotFoundException;
import org.sid.order_service.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    public OrderDTO createOrder(SaveOrderDTO saveOrderDTO) throws CustomerNotFoundException;

    public OrderDTO getOrderById(Long orderId) throws OrderNotFoundException;

    public List<OrderDTO> getAllOrders();

    public OrderDTO updateOrder(Long orderId, SaveOrderDTO saveOrderDTO) throws OrderNotFoundException;

    public void deleteOrder(Long orderId) throws OrderNotFoundException;

    List<OrderDTO> getOrdersByCustomerId(Long customerId) throws OrderNotFoundException;
}
