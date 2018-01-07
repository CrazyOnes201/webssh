package com.liu.service;

import com.liu.entity.TrainAndTicket;
import com.liu.entity.User;

public interface TicketService {
    public boolean buyTicket(TrainAndTicket buytat, User user);
}