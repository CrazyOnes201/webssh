package com.liu.action;

import com.liu.entity.TrainAndTicket;
import com.liu.service.TrainService;
import com.liu.util.FormatDate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class SearchTicketAction extends ActionSupport {
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserAction userAction;

    private String beginStation;
    private String targetStation;
    private String targetDate;
    private String advancedFlag;

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

    public String getAdvancedFlag() {
        return advancedFlag;
    }

    public void setAdvancedFlag(String advancedFlag) {
        this.advancedFlag = advancedFlag;
    }

    @Override
    public String execute() throws Exception {
        try {
            ArrayList<TrainAndTicket> tatList = trainService.getTrainList(beginStation, targetStation,
                    FormatDate.jsDateToJava(targetDate), 0);
            ActionContext act = ActionContext.getContext();

            if (userAction.isLogin()) {
                act.put("isPostResponse", "yes");
                act.put("tatList", tatList);
                return SUCCESS;
            }
            return LOGIN;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }

    public String advancedSearch() {
        try {
            ArrayList<TrainAndTicket> adtatList = trainService.getTrainList(beginStation, targetStation,
                    FormatDate.jsDateToJava(targetDate), 1);
            for (TrainAndTicket tat : adtatList) {
                System.out.println(tat);
            }
            ActionContext act = ActionContext.getContext();
            if (userAction.isLogin()) {
                act.put("isPostResponse", "yes");
                act.put("adtatList", adtatList);
                return SUCCESS;
            }

            return LOGIN;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }
}
