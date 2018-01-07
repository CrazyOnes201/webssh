package com.liu.dao;

import com.liu.entity.Usedticket;

import java.util.List;

public interface UsedTicketDAO {
    public List<Usedticket> findUsedTicket(int UserId);
}
