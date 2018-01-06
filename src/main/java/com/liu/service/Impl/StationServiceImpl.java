package com.liu.service.Impl;

import com.liu.dao.StationDAO;
import com.liu.entity.Train;
import com.liu.entity.Traininfo;
import com.liu.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationDAO stationDAO;

    public String addStation(ArrayList<Traininfo> list){
        if(stationDAO.addStation(list).equals("success"))
            return "success";
        else
            return "error";

    }



}
