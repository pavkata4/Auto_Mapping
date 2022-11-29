package com.example._16_auto_mapping.DTOS;

public class RegisterUserDTO {

    private String mail;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterUserDTO(String mail, String password, String confirmPassword, String fullName) {
        this.mail = mail;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public void setMai(String mai) {
        this.mail = mai;
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

    public String getMail() {
        return mail;
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
