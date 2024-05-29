package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name="id_user")
    private UUID id_user;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="mail")
    private String mail;

    public User(UUID id_user, String name, String surname, String mail) {
        this.id_user = id_user;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UUID getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }
}
