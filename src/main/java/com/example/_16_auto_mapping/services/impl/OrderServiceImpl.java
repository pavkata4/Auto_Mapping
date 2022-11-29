package com.example._16_auto_mapping.services.impl;

import com.example._16_auto_mapping.entities.Game;
import com.example._16_auto_mapping.entities.Order;
import com.example._16_auto_mapping.entities.ShoppingCart;
import com.example._16_auto_mapping.entities.User;
import com.example._16_auto_mapping.repositories.OrderDao;
import com.example._16_auto_mapping.services.OrderService;

import java.util.Locale;
import java.util.Optional;

public class OrderServiceImpl  implements OrderService {

    private final ShoppingCart cart;

    private final OrderDao orderDao;
///blabla
    private StringBuilder builder;

    public OrderServiceImpl(ShoppingCart cart, OrderDao orderDao) {
        this.cart = cart;
        this.orderDao = orderDao;
    }


    @Override
    public String buyItems() {
        builder.delete(0, builder.length());
       Order order = new Order();
       order.setGames(cart.getGamesToBuy());
       orderDao.saveAndFlush(order);
       builder.append("Successfully bought games:").append('\n');
       order.getGames().forEach(g->builder.append(" -").append(g.getTitle()).append('\n'));
       return builder.toString().trim();
    }
}
