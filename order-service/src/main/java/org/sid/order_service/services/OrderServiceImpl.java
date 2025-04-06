package org.sid.order_service.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.order_service.dto.OrderDTO;
import org.sid.order_service.dto.SaveOrderDTO;
import org.sid.order_service.entites.Order;
import org.sid.order_service.entites.ProductItem;
import org.sid.order_service.enumeration.OrderStatus;
import org.sid.order_service.exception.CustomerNotFoundException;
import org.sid.order_service.exception.OrderNotFoundException;
import org.sid.order_service.maper.OrderMapper;
import org.sid.order_service.modele.Customer;
import org.sid.order_service.openfeign.CustomerRestClient;
import org.sid.order_service.repositories.OrderRepository;
import org.sid.order_service.repositories.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private OrderMapper orderMapper;
    private CustomerRestClient customerRestClient;
    @Override
    public OrderDTO createOrder(SaveOrderDTO saveOrderDTO) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer =customerRestClient.getCustomerById(saveOrderDTO.getCustomerId());
            if(customer == null){
                throw  new CustomerNotFoundException("Customer Not found");
            }

        }catch (Exception e){

            throw  new CustomerNotFoundException("Customer Not found");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreated(saveOrderDTO.getCreated());
        order.setStatus(OrderStatus.CREATED);
        order.setCustomerId(saveOrderDTO.getCustomerId());
        Order savedOrder  = orderRepository.save(order);
        List<ProductItem> productItems = productItemRepository.findByOrderId(savedOrder.getId());
        savedOrder.setProductItems(productItems);

        // Mapper l'entité Order vers OrderDTO
        OrderDTO orderDTO = orderMapper.fromOrder(savedOrder);
        orderDTO.setProductItems(productItems);
        orderDTO.setCustomer(customer);
        // Ajouter les ProductItems

        return orderDTO;

    }


    @Override
    public OrderDTO getOrderById(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order Not Found"));

        OrderDTO orderDTO = orderMapper.fromOrder(order);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> orderMapper.fromOrder(order))
                .collect(Collectors.toList());
        return orderDTOList;
    }

    @Override
    public OrderDTO updateOrder(Long orderId, SaveOrderDTO saveOrderDTO) throws OrderNotFoundException {
       Order order = orderRepository.findById(orderId)
               .orElseThrow(()->new OrderNotFoundException("Order Not Found"));
       order.setCustomerId(saveOrderDTO.getCustomerId());
       order.setCreated(order.getCreated());
       order.setStatus(OrderStatus.CREATED);
       Order order1 = orderRepository.save(order);
        return orderMapper.fromOrder(order1);
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("Order Not Found"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) throws OrderNotFoundException {
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        if (orderList == null || orderList.isEmpty())
            throw new OrderNotFoundException("Order Not Found With this Id");

        List<OrderDTO> orderDTOList = orderList.stream().map(order -> orderMapper.fromOrder(order))
                    .collect(Collectors.toList());


        return orderDTOList ;
    }
}
