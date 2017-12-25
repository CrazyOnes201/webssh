package com.liu.action;

import com.liu.entity.User;
import com.liu.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginAction extends ActionSupport {
    @Autowired
    private UserService userService;

    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {
        boolean flag = userService.findUser(user);
        if(flag){
            return SUCCESS;
        }else{
            return INPUT;
        }

    }
}
