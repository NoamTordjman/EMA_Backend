package com.example.demo;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_user", columnDefinition = "UUID")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="mail")
    private String mail;

    @Column(name="password")
    private String password;


    public User() {
    }

    public User(UUID id, String name, String surname, String mail, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

    public void setId(UUID id_user) {
        this.id = id_user;
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

    public void setPassword(String password) {
        this.password = password;
    }
    public UUID getId() {
        return id;
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
