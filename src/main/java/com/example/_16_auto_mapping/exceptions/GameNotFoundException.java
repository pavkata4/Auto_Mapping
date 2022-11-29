package com.example._16_auto_mapping.exceptions;

public class GameNotFoundException extends Exception{

    public String getMessage(String title) {
        return String.format("%s not found.", title);
    }
}
