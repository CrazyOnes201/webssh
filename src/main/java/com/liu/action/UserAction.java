package com.liu.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

/**
 * Created by Crazy Ones on 2018/1/3.
 */
@Controller
public class UserAction extends ActionSupport{
    public String logOut() {
        ActionContext act = ActionContext.getContext();
        act.getSession().remove("user");

        return SUCCESS;
    }
}
