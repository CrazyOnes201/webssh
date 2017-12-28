package com.liu.dao;

import com.liu.entity.Train;
import com.liu.entity.Traininfo;

import java.util.ArrayList;
import java.util.List;

public interface TrainDAO {
    public ArrayList<List<Traininfo>> findTraininfoList(String beStation, String enStation);
    public Train getTrainById(String trainId);
}
