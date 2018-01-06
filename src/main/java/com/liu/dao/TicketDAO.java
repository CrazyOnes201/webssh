package com.liu.dao;

import com.liu.entity.Ticket;
import com.liu.entity.Traininfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Crazy Ones on 2017/12/30.
 */
public interface TicketDAO {
    public void insertEveryDayTicket();
    public ArrayList<Ticket> searchTicketInfo(String stationId, int beRank, int tarRank, Date tarDate);
    public Boolean buyOneTicket(String level, Date tarDate, List<Traininfo> trainList);
    public boolean isIllegalLevel(String level);
}
