package com.liu.service.Impl;

import com.liu.dao.UsedTicketDAO;
import com.liu.entity.Usedticket;
import com.liu.service.UsedTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsedTicketServiceImpl implements UsedTicketService {
    @Autowired
    UsedTicketDAO usedTicketDAO;


    public List<Usedticket> findUsedTicket(int userId){
        return usedTicketDAO.findUsedTicket(userId);
    }
}
