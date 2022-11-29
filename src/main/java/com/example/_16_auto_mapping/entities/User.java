package com.example._16_auto_mapping.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User{
private long id;
    private String mail;
    private String password;
    private String fullName;
   private Set<Game> games;
    private Role role;

    private Set<Order> orders;

    private BigDecimal availableCash = new BigDecimal("0");
    private ShoppingCart shoppingCart;

    public User(Set<Order> orders) {
        this.orders = new LinkedHashSet<>();
        this.availableCash = new BigDecimal("0");
        this.shoppingCart = new ShoppingCart();
    }

    public User( String mail, String password, String fullName, Role role) {

        this.mail = mail;
        this.password = password;
        this.fullName = fullName;
        this.games = new HashSet<>();
        this.role = role;
        this.availableCash = new BigDecimal("0");
        this.shoppingCart = new ShoppingCart();
    }



    @Column(name = "mail", nullable = false, unique = true)
    @Pattern(regexp = "\\w+[@]\\w+\\.[a-z]+", message = "Incorrect email.")
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Length(min = 6, message = "Too short password")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=[@#$%^&+=]*).*", message = "Password must contain at least one lowercase, uppercase and digit!")
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ManyToMany
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
@Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


     @OneToOne(targetEntity = ShoppingCart.class)
     @JoinColumn(name = "shopping_cart_id",referencedColumnName = "id")
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

      @Column(name = "available_cash")
    public BigDecimal getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(BigDecimal availableCash) {
        if (this.availableCash == null) {
            this.availableCash = new BigDecimal("0");
        }
            this.availableCash = this.availableCash.add(availableCash);
    }
    @OneToMany
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
