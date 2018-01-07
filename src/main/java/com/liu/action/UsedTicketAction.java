package com.liu.action;

import com.liu.entity.Usedticket;
import com.liu.entity.User;
import com.liu.service.UsedTicketService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UsedTicketAction extends ActionSupport {
    @Autowired
    UsedTicketService usedTicketService;
    @Autowired
    private UserAction userAction;

    private Usedticket usedticket;

    public Usedticket getUsedticket() {
        return usedticket;
    }

    public void setUsedticket(Usedticket usedticket) {
        this.usedticket = usedticket;
    }

    @Override
    public String execute() throws Exception{
        ActionContext act = ActionContext.getContext();
        User loginUser =  (User)act.getSession().get("user");

        List<Usedticket> list=usedTicketService.findUsedTicket(loginUser.getUserId());
        //如果不为空
        if(list.size()>0)
        {
            try {

                if (userAction.isLogin()) {
                    act.put("isPostResponse", "yes");
                    act.put("usedticketList", list);
                    return SUCCESS;
                }

            } catch(Exception e) {
                e.printStackTrace();
            }


        }
        return SUCCESS;
    }

}
