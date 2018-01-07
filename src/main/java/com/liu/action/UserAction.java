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
public class UserAction extends ActionSupport {
    @Autowired
    UserService userService;
    User user;
    String operation_type;//操作类型

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String logOut() {
        ActionContext act = ActionContext.getContext();
        act.getSession().remove("user");

        return SUCCESS;
    }

    public boolean isLogin() {
        ActionContext act = ActionContext.getContext();
        User loginUser = (User) act.getSession().get("user");
        if (userService.findUser(loginUser).getUsername() != null) {
            return true;
        }

        return false;
    }


    public String updateUser() {
        if (operation_type.equals("c")) {
            ActionContext act = ActionContext.getContext();
            User loginUser = (User) act.getSession().get("user");
            User user1 = userService.findUser(loginUser);
            if (user1.getUsername() != null) {
                user.setUserId(user1.getUserId());
                user.setAdmin(user1.getAdmin());
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                System.out.println(user.getIdentityNum());
                System.out.println(user.getPhone());
                userService.updateUser(user);
                return SUCCESS;

            }


            return ERROR;
        } else
            return ERROR;


    }
}
