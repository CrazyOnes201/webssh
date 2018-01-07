package com.liu.action;

import com.liu.entity.User;
import com.liu.service.TicketService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Crazy Ones on 2018/1/7.
 */
@Controller
public class RefreshTicketAction extends ActionSupport {
    @Autowired
    private UserAction userAction;
    @Autowired
    private TicketService ticketService;

    @Override
    public String execute() throws Exception {
        ActionContext act = ActionContext.getContext();
        if(userAction.isLogin() &&
                ((User)act.getSession().get("user")).getAdmin() == 1) {
            boolean isSuccess = ticketService.refreshTicket(30);
            if(isSuccess) {
                return SUCCESS;
            }
        }

        return SUCCESS;
//        return ERROR; //未处理发生未知错误情况
    }
}
