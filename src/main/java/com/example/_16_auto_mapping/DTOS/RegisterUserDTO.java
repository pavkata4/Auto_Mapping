package com.example._16_auto_mapping.DTOS;


import jakarta.validation.constraints.*;

public class RegisterUserDTO {
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, message = "Too short password")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=[@#$%^&+=]*).*", message = "Password must contain at least one lowercase, uppercase and digit!")
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterUserDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public RegisterUserDTO() {
    }

    public void setMai(String mai) {
        this.email = mai;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}
