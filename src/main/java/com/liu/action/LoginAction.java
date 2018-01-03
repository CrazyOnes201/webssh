package com.liu.action;

import com.liu.dao.TicketDAO;
import com.liu.entity.User;
import com.liu.service.TrainService;
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
    @Autowired // test
    private TrainService trainService;
    @Autowired // test
    private TicketDAO ticketDao;

    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {
        /* 仅为测试而调用 */
//        trainService.getTrainList("杭州","北京", new Date());
        /* 仅为测试而调用 */
//        ticketDao.insertEveryDayTicket();
        User nowUser = userService.findUser(user);
        boolean flag = false;
        if(nowUser.getUsername() != null) {
            flag = true;
        }
        if(flag){
            ActionContext act = ActionContext.getContext();
            act.getSession().put("user", nowUser);
            return SUCCESS;
        }else{
            return INPUT;
        }

    }
}
