package com.liu.action;

import com.liu.entity.User;
import com.liu.entity.Users;
import com.liu.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class RegistAction extends ActionSupport {
    private User user;
    //注入Service,生成SET GET方法
    private UserService userservice;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserService getUserservice() {
        return userservice;
    }
    public void setUserservice(UserService userservice) {
        this.userservice = userservice;
    }

    //execute方法
    @Override
    public String execute() throws Exception {
        if(this.userservice.saveUser(this.user)){
        return SUCCESS;
        }
        return INPUT;
    }
}
