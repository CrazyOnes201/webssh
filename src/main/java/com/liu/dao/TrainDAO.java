package com.liu.dao;

import com.liu.entity.Train;
import com.liu.entity.Traininfo;

import java.util.ArrayList;
import java.util.List;

public interface TrainDAO {
    public List<List<Traininfo>> findTraininfoList(String beStation, String enStation);
    public Train getTrainById(String trainId);
    public Train getAllTrain();
    public String addTrain(Train train);
    public String deleteTrain(Train train);
    public String updateTrain(Train train);
    public List<Traininfo> getTraininfoListByTrainId(String trainId);
    public List<List<Traininfo>> findTransferTrain(String beStation, String tarStation);
    public Traininfo getTraininfoByTrainIdAndStation(String trainId, String station);
    public List<Traininfo> selectTraininfoListByStation(String trainId, String beStation, String tarStation);
}
