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
    /**
     * 将tat中的部分变量数据合成到Usedticket中以供使用
     * @param tat 需要合成的TrainAndTicket类型数据
     * @param ut 需要加入数据的Usedticket类型变量
     * @return 是否合成成功
     */
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

    /**
     * 根据Ticket列表中的price变量大小排序结果
     * @param tList 需要排序的Ticket列表
     * @return 返回排序成功后的新列表
     */
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

//    public static ArrayList<TrainAndTicket> sortTatByPrice(ArrayList<TrainAndTicket> tatList) {
//        if(tatList == null || tatList.size() == 0) {
//            return null;
//        }
//
//        ArrayList<TrainAndTicket> newList = new ArrayList<TrainAndTicket>();
//        int length = tatList.size();
//        for(int i = 0; i < length; i += 2) {
//            int index = -1;
//            double minMiles = 9999999;
//            for(int j = 0; j < tatList.size(); j += 2) {
//                double totalMiles = tatList.get(j).getMiles() +
//                        tatList.get(j + 1).getMiles();
//                if(minMiles > totalMiles) {
//                    minMiles = totalMiles;
//                    index = j;
//                }
//            }
//            newList.add(tatList.get(index));
//            tatList.remove(index);
//            newList.add(tatList.get(index));
//            tatList.remove(index);
//        }
//
//        return newList;
//    }
}