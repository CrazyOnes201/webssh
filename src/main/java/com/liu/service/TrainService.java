package com.liu.service;

import com.liu.entity.Train;
import com.liu.entity.TrainAndTicket;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Crazy Ones on 2017/12/28.
 */
public interface TrainService {
    public ArrayList<TrainAndTicket> getTrainList(String beStation, String taStation, Date tarDate);
    public Train findTrain(String trainId);
    public Train findAllTrain();
    public String addTrain(Train train);
}