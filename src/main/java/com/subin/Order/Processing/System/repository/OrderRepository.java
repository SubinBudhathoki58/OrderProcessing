package com.subin.Order.Processing.System.repository;

import com.subin.Order.Processing.System.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
