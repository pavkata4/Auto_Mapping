package com.example._16_auto_mapping.exceptions;

public class EmailExistException extends Exception{

    private String email;

    public EmailExistException(String email) {
        this.email = email;
    }

    public String message(){
        return String.format("Email %s already exist!",email);
    }
}
