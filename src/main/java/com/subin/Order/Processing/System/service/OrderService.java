package com.subin.Order.Processing.System.service;


import com.subin.Order.Processing.System.dto.OrderDto;
import com.subin.Order.Processing.System.entity.Order;
import com.subin.Order.Processing.System.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
//placing an order
     public void placeOrder(OrderDto orderDto)
    {
        Order order = Order.builder()
                .customerName(orderDto.getCustomerName())
                .product(orderDto.getProduct())
                .price(orderDto.getPrice())
                .build();
        orderRepository.save(order);


    }
//retrieving all
    public List<Order> getAllOrder()
    {
       return orderRepository.findAll();

    }
//retrieving by id
    public Optional<Order>getOrderById(Long id)
    {
        return orderRepository.findById(id);
    }

    //Update an order

    public boolean updateOrder(Long id, OrderDto orderDto)
    {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if(existingOrder.isPresent())
        {
            Order order = existingOrder.get();
            order.setCustomerName(orderDto.getCustomerName());
            order.setProduct(orderDto.getProduct());
            order.setPrice(orderDto.getPrice());
            orderRepository.saveAndFlush(order);
            return true;
        }
        return false;
    }


    //delete an order

    public boolean deleteOrder(Long id)
    {
        Optional<Order> existingOrder= orderRepository.findById(id);
        if(existingOrder.isPresent())
        {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
