package com.liu.action;

import com.liu.entity.TrainAndTicket;
import com.liu.service.TrainService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class SearchTicketAction extends ActionSupport {
    @Autowired
    private TrainService trainService;

    private String beginStation;
    private String targetStation;
    private String targetDate;

    public String getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(String beginStation) {
        this.beginStation = beginStation;
    }

    public String getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(String targetStation) {
        this.targetStation = targetStation;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public String execute() throws Exception {
        ArrayList<TrainAndTicket> tatList = trainService.getTrainList(beginStation, targetStation,
                new Date());
        ActionContext act = ActionContext.getContext();
        /* 缺少检测用户是否登录 */
        if(true) {
            act.put("tatList", tatList);
            return SUCCESS;
        }
        return LOGIN;
    }
}
