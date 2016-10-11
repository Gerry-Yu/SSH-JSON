package com.demo.action;

import com.demo.model.User;
import com.demo.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.asm.Label;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Pinggang Yu on 2016/10/10.
 */
@Controller
public class QueryUserAction extends ActionSupport {

    @Resource
    private UserService userService;

    private int first;
    private int offset;

    public void setFirst(int first) {
        this.first = first;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @JSON(name="userList")
    public List getUserList() {
        List<User> userList =  userService.userList(first, offset);
        return userList;
    }

}
