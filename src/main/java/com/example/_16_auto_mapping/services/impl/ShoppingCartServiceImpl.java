package com.example._16_auto_mapping.services.impl;
import com.example._16_auto_mapping.entities.Game;
import com.example._16_auto_mapping.entities.ShoppingCart;
import com.example._16_auto_mapping.entities.User;
import com.example._16_auto_mapping.exceptions.GameNotFoundException;
import com.example._16_auto_mapping.exceptions.NotLoggedUserException;
import com.example._16_auto_mapping.repositories.GameDao;
import com.example._16_auto_mapping.repositories.ShoppingCartDao;
import com.example._16_auto_mapping.repositories.UserDao;
import com.example._16_auto_mapping.services.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingCartServiceImpl implements ShoppingCartServices {

    private final ShoppingCartDao shoppingCartDao;

    private final GameDao gameDao;

    private StringBuilder sb;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, UserDao userDao, GameDao gameDao, GameDao gameDao1) {
        this.shoppingCartDao = shoppingCartDao;
        this.gameDao = gameDao1;

        sb = new StringBuilder();
    }

    @Override
    public String AddItem(String title, Optional<User> loggedUser) {
        Optional<Game> game = gameDao.findByTitle(title);
        try {
            shoppingCartDao.save(loggedUser.get().getShoppingCart().addItem(game));

        } catch (GameNotFoundException e) {
            return e.getMessage(title);
        }
        return String.format("%s added to cart", title);
    }

    @Override
    public String removeItem(String title, Optional<User> loggedUser) throws GameNotFoundException {
      Optional<Game>  game =  gameDao.findByTitle(title);
      if (game.isPresent() && loggedUser.get().getShoppingCart().getGamesToBuy().contains(game.get())){
          shoppingCartDao.save(loggedUser.get().getShoppingCart().removeItem(game.get()));
      }else {
          throw new GameNotFoundException();
      }
      return String.format("%s removed from cart.", title);
    }

    @Override
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartDao.saveAndFlush(shoppingCart);
        return shoppingCart;
    }


}
