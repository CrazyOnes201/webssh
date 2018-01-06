package com.liu.service.Impl;

import com.liu.dao.TicketDAO;
import com.liu.dao.TrainDAO;
import com.liu.entity.Ticket;
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
    @Autowired
    TicketDAO ticketDao;

    /**
     * 获取车次及对应车票数的
     * @param beStation 开始车站
     * @param taStation 目的车站
     * @param tarDate   查询日期
     * @return 返回可用于显示的车次及对应车票实体
     */
    public ArrayList<TrainAndTicket> getTrainList(String beStation, String taStation, Date tarDate) {
        List<List<Traininfo>> infoList = trainDao.findTraininfoList(beStation, taStation);
        List<Traininfo> startTrain = infoList.get(0);
        List<Traininfo> targetTrain = infoList.get(1);
        ArrayList<TrainAndTicket> resultList = new ArrayList<TrainAndTicket>();
        int listLength = startTrain.size();
        for(int i = 0; i < listLength; ++i) {
            TrainAndTicket elem = new TrainAndTicket(startTrain.get(i),
                    targetTrain.get(i));
            ArrayList<Ticket> tList = ticketDao.searchTicketInfo(startTrain.get(i).getTrainId(), startTrain.get(i).getRank(),
                    targetTrain.get(i).getRank());  //tarDate
            elem.setTicketList(tList);
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

    //查找
    public Train findTrain(String trainId){

        return trainDao.getTrainById(trainId);
    }
    public Train findAllTrain(){

        return trainDao.getAllTrain();
    }

    //添加车次
    public String addTrain(Train train){
        Train firsttrain = trainDao.getTrainById(train.getTrainId());

        if(firsttrain==null) {
            return trainDao.addTrain(train);
        }
        return "error";

    }

    public String deleteTrain(Train train) {
        return trainDao.deleteTrain(train);
    }

    public String updateTrain(Train train) {
        return trainDao.updateTrain(train);
    }

    //   public User findUser(User user){
//        return userDao.findUser(user);
//    }




}
