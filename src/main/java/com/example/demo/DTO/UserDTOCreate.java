package com.example.demo.DTO;

public class UserDTOCreate {
    private String name;
    private String surname;
    private String mail;
    private String password;





    public void setName(String name) {
        this.name = name;
    }

    public UserDTOCreate(String name, String surname, String mail,String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
