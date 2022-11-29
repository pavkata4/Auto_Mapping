package com.example._16_auto_mapping.entities;

import com.example._16_auto_mapping.exceptions.GameNotFoundException;
import com.example._16_auto_mapping.repositories.GameDao;
import org.hibernate.annotations.Cascade;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.lang.annotation.Retention;
import java.util.*;

@Entity
public class ShoppingCart{

    private Long id;

    private Set<Game> gamesToBuy;


    public ShoppingCart(GameDao gameDao) {
        this.gamesToBuy = new LinkedHashSet<>();
    }

    public ShoppingCart() {
     this.gamesToBuy = new LinkedHashSet<>();
    }

    public ShoppingCart addItem(Optional<Game> game) throws GameNotFoundException {
        if (game.isPresent()){
            gamesToBuy.add(game.get());
        }else {
            throw new GameNotFoundException();
        }
        return this;
    }
    public ShoppingCart removeItem(Game game){
        gamesToBuy.remove(game);
        return this;
    }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shopping_cart_id_game_to_buy_id", joinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "game_id",referencedColumnName = "id"))

    public Set<Game> getGamesToBuy() {
        return gamesToBuy;
    }

    public void setGamesToBuy(Set<Game> gamesToBuy) {
        this.gamesToBuy = gamesToBuy;
    }

}
