package com.liu.action;

import com.liu.entity.User;
import com.liu.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private User user;
    //注入Service，生成Set和Get方法
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

    @Override
    public String execute() throws Exception {
        boolean flag = userservice.findUser(user);
        if(flag){
            return SUCCESS;
        }else{
            return INPUT;
        }

    }
}
