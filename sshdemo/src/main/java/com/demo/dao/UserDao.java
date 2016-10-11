package com.demo.dao;

import com.demo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pinggang Yu on 2016/10/9.
 */
public interface UserDao {
    int saveUser(User user);
    List<User> userList(int first, int offset);
}
