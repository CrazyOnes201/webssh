package com.liu.dao;

import com.liu.entity.Trainstation;

import java.util.List;

public interface TicketDAO {
    public List<Trainstation> findTrain();
}
