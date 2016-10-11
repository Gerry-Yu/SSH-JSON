package com.demo.service;

import com.demo.model.User;

import java.util.List;

/**
 * Created by Pinggang Yu on 2016/10/9.
 */
public interface UserService {
    int addUser(User user);
    List<User> userList (int first, int offset);
}
