package com.example._16_auto_mapping.repositories;

import com.example._16_auto_mapping.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
