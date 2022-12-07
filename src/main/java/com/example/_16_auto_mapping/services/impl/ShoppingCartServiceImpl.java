package com.example._16_auto_mapping.services.impl;
import com.example._16_auto_mapping.entities.Game;
import com.example._16_auto_mapping.entities.ShoppingCart;
import com.example._16_auto_mapping.entities.User;
import com.example._16_auto_mapping.exceptions.GameNotFoundException;
import com.example._16_auto_mapping.repositories.GameRepository;
import com.example._16_auto_mapping.repositories.ShoppingCartRepository;

import com.example._16_auto_mapping.services.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartServices {

    private final ShoppingCartRepository shoppingCartRepository;

    private final GameRepository gameRepository;

    private StringBuilder sb;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, GameRepository gameRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.gameRepository = gameRepository;

        sb = new StringBuilder();
    }

    @Override
    public String AddItem(String title, Optional<User> loggedUser) {
        Optional<Game> game = gameRepository.findByTitle(title);
        try {
            shoppingCartRepository.save(loggedUser.get().getShoppingCart().addItem(game));

        } catch (GameNotFoundException e) {
            return e.getMessage(title);
        }
        return String.format("%s added to cart", title);
    }

    @Override
    public String removeItem(String title, Optional<User> loggedUser) throws GameNotFoundException {
      Optional<Game>  game =  gameRepository.findByTitle(title);
      if (game.isPresent() && loggedUser.get().getShoppingCart().getGamesToBuy().contains(game.get())){
          shoppingCartRepository.save(loggedUser.get().getShoppingCart().removeItem(game.get()));
      }else {
          throw new GameNotFoundException();
      }
      return String.format("%s removed from cart.", title);
    }

    @Override
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.saveAndFlush(shoppingCart);
        return shoppingCart;
    }


}
