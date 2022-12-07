package com.example._16_auto_mapping.entities;

import com.example._16_auto_mapping.exceptions.GameNotFoundException;
import com.example._16_auto_mapping.repositories.GameRepository;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "shopping_cards")
public class ShoppingCart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany
//    @JoinTable(name = "shopping_cart_id_game_to_buy_id", joinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "game_id",referencedColumnName = "id"))
    private Set<Game> gamesToBuy;


    public ShoppingCart() {
     this.gamesToBuy = new LinkedHashSet<>();
    }
    public ShoppingCart(GameRepository gameRepository) {
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Game> getGamesToBuy() {
        return gamesToBuy;
    }

    public void setGamesToBuy(Set<Game> gamesToBuy) {
        this.gamesToBuy = gamesToBuy;
    }

}
