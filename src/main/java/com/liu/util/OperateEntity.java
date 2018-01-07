package com.liu.util;

import com.liu.entity.Ticket;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Usedticket;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Crazy Ones on 2018/1/7.
 */
public class OperateEntity {
    public static boolean tatToUsedticket(TrainAndTicket tat, Usedticket ut) {
        ut.setTrainId(tat.getTrainId());
        ut.setStartStationId(tat.getBeginStation());
        ut.setEndStationId(tat.getTargetStation());
        Ticket t = tat.getTicketList().get(0);
        ut.setLevel(t.getLevel());
        ut.setMoney(t.getPrice());
        try {
            String parseDate = tat.getsDate() + " " + tat.getBeginTime();
            Date beDate = DateFormat.getDateTimeInstance().parse("2018-02-01 12:41:00");
            ut.setDate(beDate);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
