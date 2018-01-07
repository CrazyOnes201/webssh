package com.liu.service.Impl;

import com.liu.dao.TicketDAO;
import com.liu.dao.TrainDAO;
import com.liu.entity.Ticket;
import com.liu.entity.Train;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Traininfo;
import com.liu.service.TrainService;
import com.liu.util.FormatDate;
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
     * @param flag 标志是否为高级查询
     * @return 返回可用于显示的车次及对应车票实体
     */
    public ArrayList<TrainAndTicket> getTrainList(String beStation, String taStation, Date tarDate, int flag) {
        List<List<Traininfo>> infoList = null;
        if(flag == 0) {
            infoList = trainDao.findTraininfoList(beStation, taStation);
        } else {
            infoList = trainDao.findTransferTrain(beStation, taStation);
        }
        List<Traininfo> startTrain = infoList.get(0);
        List<Traininfo> targetTrain = infoList.get(1);
        ArrayList<TrainAndTicket> resultList = new ArrayList<TrainAndTicket>();
        int listLength = startTrain.size();
        String sDate = FormatDate.javaDateToSql(tarDate);
        for(int i = 0; i < listLength; ++i) {
            TrainAndTicket elem = new TrainAndTicket(startTrain.get(i),
                    targetTrain.get(i), sDate);
            if(startTrain.get(i).getRank() == 1) {
                elem.setStart(true);
            } else {
                elem.setStart(false);
            }

            Train train = trainDao.getTrainById(startTrain.get(i).getTrainId());
            if(train.getEndStation().equals(targetTrain.get(i).getStation())) {
                elem.setEnd(true);
            } else{
                elem.setEnd(false);
            }

            ArrayList<Ticket> tList = ticketDao.searchTicketInfo(startTrain.get(i).getTrainId(), startTrain.get(i).getRank(),
                    targetTrain.get(i).getRank(), sDate);  //还有隔天查询
            elem.setTicketList(tList);
            resultList.add(elem);
        }

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

}
