package com.liu.dao;

import com.liu.entity.Traininfo;

import java.util.ArrayList;

public interface StationDAO {
    public String addStation(ArrayList<Traininfo> list);
    public Traininfo findStation(Traininfo traininfo);
}
