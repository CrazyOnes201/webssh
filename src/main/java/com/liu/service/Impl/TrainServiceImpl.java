package com.liu.service.Impl;

import com.liu.dao.TrainDAO;
import com.liu.entity.Train;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Traininfo;
import com.liu.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Crazy Ones on 2017/12/28.
 */
@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    TrainDAO trainDao;

    public ArrayList<TrainAndTicket> getTrainList(String beStation, String taStation, Date tarDate) {
        ArrayList<List<Traininfo>> infoList = trainDao.findTraininfoList(beStation, taStation);
        List<Traininfo> startTrain = infoList.get(0);
        List<Traininfo> targetTrain = infoList.get(1);
        ArrayList<TrainAndTicket> resultList = new ArrayList<TrainAndTicket>();
        int listLength = startTrain.size();
        for(int i = 0; i < listLength; ++i) {
            TrainAndTicket elem = new TrainAndTicket(startTrain.get(i),
                    targetTrain.get(i));
            resultList.add(elem);
        }

        for(int i = 0; i < listLength; ++i) {
            Train train = trainDao.getTrainById(resultList.get(i).getTrainId());
            if(train.getStartStation().equals(resultList.get(i).getBeginStation())) {
                resultList.get(i).setStart(true);
            } else {
                resultList.get(i).setStart(false);
            }
            if(train.getEndStation().equals(resultList.get(i).getTargetStation())) {
                resultList.get(i).setEnd(true);
            } else {
                resultList.get(i).setEnd(false);
            }
        }

        for(TrainAndTicket elem: resultList) {
            System.out.println(elem);
        }
        System.out.println(tarDate);

        return resultList;
    }
}
