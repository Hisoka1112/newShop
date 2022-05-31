package com.example.shop1;

import java.math.BigDecimal;

public class User {
    private int id;
    private String login;
    private String password;
    private String country;
    private String email;
    private  Gender gender;
    private BigDecimal money;
    public User(int id, String login, String password, String country, String email, Gender gender) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.country = country;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public User(){

    }

    public User(int id, String login, String password, String country, String email, Gender gender, BigDecimal money) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.country = country;
        this.email = email;
        this.gender = gender;
        this.money = money;
    }
}
