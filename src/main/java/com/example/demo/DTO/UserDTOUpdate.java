package com.example.demo.DTO;

import java.util.UUID;

public class UserDTOUpdate {
    private String mail;
    private UUID Userid;

    public void setUserid(UUID userid) {
        Userid = userid;
    }

    public UUID getUserid() {
        return Userid;
    }

    public void setMail(String mail) {
        this.mail = mail;

    }


    public String getMail() {
        return mail;
    }
}
