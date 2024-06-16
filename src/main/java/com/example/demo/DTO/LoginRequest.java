package com.example.demo.DTO;



public class LoginRequest {
    private String mail;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return mail;
    }

    public void setUsername(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
