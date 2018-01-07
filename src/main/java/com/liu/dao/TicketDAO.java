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
    public void insertDayTicket(Date tarDate);
    public ArrayList<Ticket> searchTicketInfo(String stationId, int beRank, int tarRank, String tarDate);
    public Boolean buyOneTicket(String level, String tarDate, List<Traininfo> trainList);
    public boolean isIllegalLevel(String level);
    public boolean ticketIsCreated(Date checkDate);
    public boolean removeIllegalTicket(Date leftLimit, Date rightLimit);
}
