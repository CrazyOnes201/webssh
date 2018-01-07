package com.liu.action;

import com.liu.entity.User;
import com.liu.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Crazy Ones on 2018/1/3.
 */
@Controller
public class UserAction extends ActionSupport{
    @Autowired
    UserService userService;

    public String logOut() {
        ActionContext act = ActionContext.getContext();
        act.getSession().remove("user");

        return SUCCESS;
    }

    public boolean isLogin() {
        ActionContext act = ActionContext.getContext();
        User loginUser =  (User)act.getSession().get("user");
        if(userService.findUser(loginUser).getUsername() != null) {
            return true;
        }

        return false;
    }

    public String updateUser(User user){

        



        return "error";
    }




}
