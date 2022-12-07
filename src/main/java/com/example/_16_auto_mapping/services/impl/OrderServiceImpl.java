package com.example._16_auto_mapping.services.impl;

import com.example._16_auto_mapping.entities.Order;
import com.example._16_auto_mapping.entities.ShoppingCart;
import com.example._16_auto_mapping.repositories.OrderRepository;
import com.example._16_auto_mapping.services.OrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl  implements OrderService {

    //private final ShoppingCart cart;

    private final OrderRepository orderRepository;

    private StringBuilder builder;

    public OrderServiceImpl(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }


    @Override
    public String buyItems(ShoppingCart cart) {
        builder.delete(0, builder.length());
       Order order = new Order();
       order.setGames(cart.getGamesToBuy());
       orderRepository.saveAndFlush(order);
       builder.append("Successfully bought games:").append('\n');
       order.getGames().forEach(g->builder.append(" -").append(g.getTitle()).append('\n'));
       return builder.toString().trim();
    }
}
