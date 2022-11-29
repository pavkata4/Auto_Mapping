package com.example._16_auto_mapping;


import com.example._16_auto_mapping.DTOS.LoginUserDTO;
import com.example._16_auto_mapping.DTOS.RegisterUserDTO;
import com.example._16_auto_mapping.entities.Game;
import com.example._16_auto_mapping.entities.Role;
import com.example._16_auto_mapping.services.GameService;
import com.example._16_auto_mapping.services.OrderService;
import com.example._16_auto_mapping.services.ShoppingCartServices;
import com.example._16_auto_mapping.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private final UserService userService;

    private final GameService gameService;

    private final OrderService orderService;

    private final ShoppingCartServices shoppingCardServices;

    public Runner(UserService userService, GameService gameService, OrderService orderService, ShoppingCartServices shoppingCardServices) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.shoppingCardServices = shoppingCardServices;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

         while (true) {
        String[] input = scanner.nextLine().split("\\|");
             switch (input[0]) {
                 case "RegisterUser":
                     String mail = input[1];
                     String password = input[2];
                     String confirmPassword = input[3];
                     String fullName = input[4];
                     RegisterUserDTO registerUserDTO = new RegisterUserDTO(mail, password, confirmPassword, fullName);
                     System.out.println(this.userService.registerUser(registerUserDTO));
                     break;
                 case "LoginUser":
                     mail = input[1];
                     password = input[2];
                     System.out.println(userService.loginUser(new LoginUserDTO(mail, password)));
                     break;
                 case "Logout":
                     System.out.println(userService.logout());
                     break;
                 case "AddGame":
                     if (userService.getLoggedUser() != null) {
                         if (userService.getLoggedUser().get().getRole().equals(Role.ADMIN)) {
                             String title = input[1];
                             BigDecimal price = BigDecimal.valueOf(Double.parseDouble(input[2]));
                             double size = Double.parseDouble(input[3]);
                             String trailer = input[4];
                             String thumbnailURL = input[5];
                             String description = input[6];
                             LocalDate date = LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                             System.out.println(gameService.addGame(new Game(title, trailer, thumbnailURL, size, price, description, date)));
                         } else {
                             System.out.println("You cannot rights to add new games.");
                         }
                     }else {
                         System.out.println("You are not logged in");
                     }

                     break;
                 case "EditGame":
                     if (userService.getLoggedUser()!=null) {
                         if (userService.getLoggedUser().get().getRole().equals(Role.ADMIN)) {
                             List<String> values = new ArrayList<>(Arrays.asList(input).subList(1, input.length));
                             System.out.println(gameService.editGame(Long.parseLong(input[1]), values));
                         } else {
                             System.out.println("You cannot rights to edit games.");
                         }
                     }else {
                         System.out.println("You are not logged in.");
                     }
                         break;
                 case "DeleteGame":
                     if (userService.getLoggedUser().isPresent()) {
                         if (userService.getLoggedUser().get().getRole().equals(Role.ADMIN)) {
                             gameService.deleteGame(Long.parseLong(input[1]));
                         }
                     }
                     break;
                 case "AllGames":
                  gameService.viewAllGames();
                  break;
                 case "DetailGame":
                     String title = input[1];
                     System.out.println(gameService.findGame(title));
                     break;
                 case"OwnedGames":
                     if (userService.getLoggedUser().isPresent()){
                         for (Game game : userService.getLoggedUser().get().getGames()) {
                             System.out.println(game.getTitle());
                         }
                     }else {
                         System.out.println("You must logged in.");
                     }
                     break;
                 case "AddItem":
                     title = input[1];
                     if (userService.getLoggedUser() != null){
                         System.out.println(shoppingCardServices.AddItem(title, userService.getLoggedUser()));
                     }else {
                         System.out.println("You must logged in.");
                     }
                     break;
                 case "BuyItem":
                     System.out.println((userService.getLoggedUser().get()));
                     break;

                 case "Deposit":
                     BigDecimal deposit  = new BigDecimal(input[1]);
                     userService.getLoggedUser().get().setAvailableCash(deposit);
                     break;

             }
         }
    }
}
