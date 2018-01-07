package com.liu.action;

import com.liu.entity.Ticket;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.User;
import com.liu.service.TicketService;
import com.liu.util.OperateString;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crazy Ones on 2018/1/6.
 */
@Controller
public class BuyTicketAction extends ActionSupport {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserAction userAction;

    private String tarLevel1;
    private String tarLevel2;
    private int tarIndex;

    public String getTarLevel1() {
        return tarLevel1;
    }

    public void setTarLevel1(String tarLevel1) {
        this.tarLevel1 = tarLevel1;
    }

    public String getTarLevel2() {
        return tarLevel2;
    }

    public void setTarLevel2(String tarLevel2) {
        this.tarLevel2 = tarLevel2;
    }

    public int getTarIndex() {
        return tarIndex;
    }

    public void setTarIndex(int tarIndex) {
        this.tarIndex = tarIndex;
    }

    @Override
    public String execute() throws Exception {
        if(!userAction.isLogin()) {
            return LOGIN;
        }
        ActionContext act = ActionContext.getContext();
        List<TrainAndTicket> tatList = (List<TrainAndTicket>)act.getSession().get("tatList");
        if(tatList == null || tatList.size() < 1 ||
                tarIndex < 0 || tarIndex > tatList.size() - 1) {
            return ERROR;
        }
        TrainAndTicket tarTicket = tatList.get(tarIndex);
        if(act.getSession().get("tarTicket") != null) {
            act.getSession().remove("tarTicket");
        }
        act.getSession().put("tarTicket", tarTicket);

        return SUCCESS;
    }

    public String buyOneTicket() {
        if(!userAction.isLogin()) {
            return LOGIN;
        }
        ActionContext act = ActionContext.getContext();
        if(act.getSession().get("tarTicket") != null && !OperateString.stringIsEmpty(tarLevel1)) {
            User nowUser = (User)act.getSession().get("user");
            TrainAndTicket tarTicket = (TrainAndTicket)act.getSession().get("tarTicket");
            ArrayList<Ticket> tList = tarTicket.getTicketList();

            for(int i = 0; i < tList.size(); ++i) {
                if(tList.get(i).getLevel().equals(tarLevel1)) {
                    ArrayList<Ticket> newTList = new ArrayList<Ticket>();
                    newTList.add(tList.get(i));
                    tarTicket.setTicketList(newTList);
                    boolean isSuccess = ticketService.buyTicket(tarTicket, nowUser);
                    if(isSuccess) {
                        act.getSession().remove("tatList");
                        return SUCCESS;
                    }
                }
            }
            act.getSession().remove("tarTicket");
        }

        return ERROR;
    }
}
