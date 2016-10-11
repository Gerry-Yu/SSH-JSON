package com.demo.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Pinggang Yu on 2016/10/9.
 */
@Component("user")
@Entity
@Table(name="usertt")
public class User {
    @Id
/*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
*/
    private int userId;
    private String username;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
