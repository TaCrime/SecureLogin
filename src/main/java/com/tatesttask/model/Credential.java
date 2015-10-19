package com.tatesttask.model;

import com.tatesttask.util.CryptoConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Credential implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String login;
    @Convert(converter = CryptoConverter.class)
    private String password;

    public Credential() {
    }

    public Credential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "" + login + " " + password;
    }

}
