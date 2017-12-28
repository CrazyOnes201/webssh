package com.liu.action;

import com.liu.entity.User;
import com.liu.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegistAction extends ActionSupport {
    @Autowired
    private UserService userService;

    private User user;
    //注入Service,生成SET GET方法
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    //execute方法
    @Override
    public String execute() throws Exception {
        if(this.userService.saveUser(this.user)){
        return SUCCESS;
        }
        return INPUT;
    }
}
