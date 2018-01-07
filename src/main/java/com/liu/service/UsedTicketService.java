package com.liu.service;

import com.liu.entity.Usedticket;

import java.util.List;

public interface UsedTicketService {
    public List<Usedticket> findUsedTicket(int userId);
}
