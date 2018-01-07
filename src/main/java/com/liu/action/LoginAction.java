package com.liu.action;

import com.liu.entity.User;
import com.liu.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

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
        User nowUser = userService.findUser(user);
        boolean flag = false;
        if(nowUser.getUsername() != null) {
            flag = true;
        }
        if(flag){
            ActionContext act = ActionContext.getContext();
            act.getSession().put("user", nowUser);
            if(nowUser.getAdmin()==1) return LOGIN;
            return SUCCESS;
        }else{
            return INPUT;
        }

    }
}
