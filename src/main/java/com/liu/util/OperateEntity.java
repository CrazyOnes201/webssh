package com.liu.util;

import com.liu.entity.Ticket;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Usedticket;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
            Date beDate = DateFormat.getDateTimeInstance().parse(parseDate);
            ut.setDate(beDate);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<Ticket> tatSortTicketList(ArrayList<Ticket> tList) {
        if(tList == null || tList.size() == 0) {
            return null;
        }

        int length = tList.size();
        ArrayList<Ticket> result = new ArrayList<Ticket>();
        for(int i = 0; i < length - 1; ++i) {
            int index = -1;
            double max = -1;
            for(int j = 0; j < tList.size(); ++j) {
                if(max < tList.get(j).getPrice() && !tList.get(j).getLevel().equals("无座")) {
                    max = tList.get(j).getPrice();
                    index = j;
                }
            }
            result.add(tList.get(index));
            tList.remove(index);
        }
        if(tList.size() != 0) {
            result.add(tList.get(0));
        }

        return result;
    }
}
