package com.subin.Order.Processing.System.controller;

import com.subin.Order.Processing.System.dto.OrderDto;
import com.subin.Order.Processing.System.entity.Order;
import com.subin.Order.Processing.System.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Valid
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrderDto orderDto)
    {
        orderService.placeOrder(orderDto);//passing data coming from the client to service
        return ResponseEntity.ok("Order placed successfully");
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAll()
    {
        List<Order> orders = orderService.getAllOrder();
        if(orders.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getbyid/{id}")

    public ResponseEntity<Order> getById(@PathVariable Long id)
    {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.noContent().build());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto)
    {
            boolean isUpdated = orderService.updateOrder(id,orderDto);
            if(isUpdated)
            {
                return ResponseEntity.ok("order updated");
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
    }


    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteOrder(@PathVariable Long id)
    {
        boolean isDeleted = orderService.deleteOrder(id);
        if(isDeleted)
        {
            return ResponseEntity.ok("order deleted successfully");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }






}
