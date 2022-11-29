package com.example._16_auto_mapping.services;

import com.example._16_auto_mapping.DTOS.LoginUserDTO;
import com.example._16_auto_mapping.DTOS.RegisterUserDTO;
import com.example._16_auto_mapping.entities.User;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {
    public  String registerUser(RegisterUserDTO user);

    public String loginUser(LoginUserDTO user);

    String logout();

    Optional<User> getLoggedUser();

    public String deposit(BigDecimal bigDecimal);
}
