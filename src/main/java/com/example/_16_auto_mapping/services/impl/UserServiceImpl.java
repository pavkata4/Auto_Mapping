package com.example._16_auto_mapping.services.impl;

import com.example._16_auto_mapping.DTOS.LoginUserDTO;
import com.example._16_auto_mapping.DTOS.RegisterUserDTO;
import com.example._16_auto_mapping.entities.Role;
import com.example._16_auto_mapping.entities.ShoppingCart;
import com.example._16_auto_mapping.entities.User;
import com.example._16_auto_mapping.repositories.UserDao;
import com.example._16_auto_mapping.services.ShoppingCartServices;
import com.example._16_auto_mapping.services.UserService;
import com.example._16_auto_mapping.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ShoppingCartServices shoppingCartServices;

    private final StringBuilder sb;
    private final UserDao userDao;

    private final ModelMapper mapper;

    private final ValidatorUtil validatorUtil;

    private Optional <User> loggedUser;
@Autowired
    public UserServiceImpl(ShoppingCartServices shoppingCartServices, UserDao userDao, ModelMapper mapper, ValidatorUtil validatorUtil) {
    this.shoppingCartServices = shoppingCartServices;
    this.sb = new StringBuilder();
    this.userDao = userDao;
        this.mapper = mapper;
    this.validatorUtil = validatorUtil;
}
    @Override
    public Optional<User> getLoggedUser() {
        return loggedUser;
    }

    @Override
    public String deposit(BigDecimal cash) {
    sb.delete(0, sb.length());
        if (loggedUser.isPresent()){
            loggedUser.get().setAvailableCash(cash);
            sb.append(String.format("BGN %.2f successfully deposited", cash));
        }else {
            sb.append("You must logged in first.");
        }
        return sb.toString();
    }

    @Override
    public String registerUser(RegisterUserDTO user){
    sb.delete(0,sb.length());
       User user1;
       user1 = mapper.map(user, User.class);
       user1.setShoppingCart(shoppingCartServices.addShoppingCart(new ShoppingCart()));
    if (validatorUtil.isValid(user1)) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (userDao.findAll().isEmpty()) {
                user1.setRole(Role.ADMIN);
            } else {
                user1.setRole(Role.USER);
            }
           // try {
                userDao.saveAndFlush(user1);
                sb.append(String.format("%s was registered!", user.getFullName()));
           // } catch (Exception e) {
             //   System.out.printf("Email %s already exist", user1.getMail());
           // }
        }else {
            sb.append("Passwords are not equals.");

        }

    }else {
        validatorUtil.violations(user1).forEach(e -> sb.append(e.getMessage()).append(" "));
    }
    return sb.toString().trim();
    }

    @Override
    public String loginUser(LoginUserDTO user) {
    sb.delete(0, sb.length());
    if(loggedUser == null){
        loggedUser = userDao.findByMailAndPassword(user.getMail(), user.getPassword());
        if (loggedUser.isEmpty()){
            sb.append("Incorrect username / password");
            loggedUser = null;
        }else {
            sb.append(String.format("Successfully logged in %s", loggedUser.get().getFullName()));
        }
    }else {
        sb.append("You must logged out first!");
    }


   return sb.toString().trim();
}

    @Override
    public String  logout() {
    sb.delete(0,sb.length());
        if (loggedUser.isPresent()){
            sb.append(String.format("User %s successfully logged out",loggedUser.get().getFullName()));
            loggedUser = null;
        }else {
            sb.append("Cannot log out. No user was logged in.");
        }
        return sb.toString().trim();
    }





}
