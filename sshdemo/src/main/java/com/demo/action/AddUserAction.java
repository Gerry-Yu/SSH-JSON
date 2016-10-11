package com.demo.action;

import com.demo.model.User;
import com.demo.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;


/**
 * Created by Pinggang Yu on 2016/10/9.
 */
@Controller
public class AddUserAction extends ActionSupport {
    private int userId;
    private String username;
    private String password;
    private String result;
    @Resource
    private UserService userService;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String execute() throws Exception {

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        System.out.println(user.toString());
        try {
            userService.addUser(user);
            this.result = "true";
        } catch (Exception e) {
            System.out.println(user.toString());
            this.result = "false";
        }
        return SUCCESS;
    }
}
