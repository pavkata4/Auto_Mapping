package com.example._16_auto_mapping.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
private long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;

    @Column(name = "available_cash")
    private BigDecimal availableCash;
    @OneToOne(targetEntity = ShoppingCart.class)
//    @JoinColumn(name = "shopping_cart_id",referencedColumnName = "id")
   private ShoppingCart shoppingCart;

    public User(Set<Order> orders) {
        this.orders = new LinkedHashSet<>();
        this.availableCash = new BigDecimal("0");
       this.shoppingCart = new ShoppingCart();
    }

    public User() {

    }
    public User( String mail, String password, String fullName, Role role) {

        this.email = mail;
        this.password = password;
        this.fullName = fullName;
        this.games = new HashSet<>();
        this.role = role;
        this.availableCash = new BigDecimal("0");
        this.shoppingCart = new ShoppingCart();
    }




//    @Pattern(regexp = "\\w+[@]\\w+\\.[a-z]+", message = "Incorrect email.")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

//    @Length(min = 6, message = "Too short password")
//    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=[@#$%^&+=]*).*", message = "Password must contain at least one lowercase, uppercase and digit!")

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



     public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


    public BigDecimal getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(BigDecimal availableCash) {
        if (this.availableCash == null) {
            this.availableCash = new BigDecimal("0");
        }
            this.availableCash = this.availableCash.add(availableCash);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
