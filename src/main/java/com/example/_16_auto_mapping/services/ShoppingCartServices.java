package com.example._16_auto_mapping.services;


import com.example._16_auto_mapping.entities.ShoppingCart;
import com.example._16_auto_mapping.entities.User;
import com.example._16_auto_mapping.exceptions.GameNotFoundException;

import java.util.Optional;

public interface ShoppingCartServices {

    String AddItem(String title, Optional<User> user);

    String removeItem(String title, Optional<User> loggedUser) throws GameNotFoundException;


    ShoppingCart addShoppingCart(ShoppingCart shoppingCart);
}
