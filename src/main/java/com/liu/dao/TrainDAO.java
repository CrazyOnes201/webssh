package com.liu.dao;

import com.liu.entity.Traininfo;
import java.util.List;

public interface TrainDAO {
    public List<Traininfo> findTrain(String beStation, String enStation);
}
