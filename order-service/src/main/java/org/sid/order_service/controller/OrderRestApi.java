package org.sid.order_service.controller;

import lombok.AllArgsConstructor;
import org.sid.order_service.dto.OrderDTO;
import org.sid.order_service.dto.SaveOrderDTO;
import org.sid.order_service.exception.CustomerNotFoundException;
import org.sid.order_service.exception.OrderNotFoundException;
import org.sid.order_service.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api")
public class OrderRestApi {
    private OrderService orderService;

    @PostMapping("/orders")
    public OrderDTO createOrder(@RequestBody SaveOrderDTO saveOrderDTO) throws CustomerNotFoundException {
        return orderService.createOrder(saveOrderDTO);
    }

    @GetMapping("/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) throws CustomerNotFoundException, OrderNotFoundException {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrderById(@PathVariable Long orderId) throws CustomerNotFoundException, OrderNotFoundException {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/orders/{orderId}")
    public OrderDTO updateOrderById(@PathVariable Long orderId,@RequestBody SaveOrderDTO saveOrderDTO) throws CustomerNotFoundException, OrderNotFoundException {
        return orderService.updateOrder(orderId, saveOrderDTO);
    }

    @GetMapping("/orders/{customerId}")
    public List<OrderDTO> findOrderByCustomerId(@PathVariable Long customerId) throws CustomerNotFoundException, OrderNotFoundException {
        return orderService.getOrdersByCustomerId(customerId);
    }
}
