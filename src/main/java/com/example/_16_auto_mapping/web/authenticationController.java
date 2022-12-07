package com.example._16_auto_mapping.web;

import com.example._16_auto_mapping.DTOS.RegisterUserDTO;
import com.example._16_auto_mapping.services.UserService;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class authenticationController  {

    private final UserService userService;

    public authenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public ResponseEntity<RegisterUserDTO> register(@RequestBody @Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String s = userService.registerUser(registerUserDTO);
        if ((String.format("%s was registered.", registerUserDTO.getFullName()).equals(s))){
            return new ResponseEntity<>(registerUserDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
