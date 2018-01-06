package com.liu.dao;

import com.liu.entity.Ticket;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Crazy Ones on 2017/12/30.
 */
public interface TicketDAO {
    public void insertEveryDayTicket();
    public ArrayList<Ticket> searchTicketInfo(String stationId, int beRank, int tarRank, Date tarDate);
}
