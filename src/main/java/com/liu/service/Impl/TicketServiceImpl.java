package com.liu.service.Impl;

import com.liu.dao.TicketDAO;
import com.liu.dao.TrainDAO;
import com.liu.dao.UserDAO;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Traininfo;
import com.liu.entity.Usedticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl {
    @Autowired
    TicketDAO ticketDao;
    @Autowired
    TrainDAO trainDao;
    @Autowired
    UserDAO userDao;

    public boolean buyTicket(TrainAndTicket buytat) {
        String level = "";  //是否合法
        Date tarDate = new Date();
        boolean isSuccess = false;

        List<Traininfo> tarTraininfoList = trainDao.selectTraininfoListByStation(buytat.getTrainId(),
                buytat.getBeginStation(), buytat.getTargetStation());
        if(ticketDao.buyOneTicket(level, tarDate, tarTraininfoList)) {
            Usedticket ut = new Usedticket();
            isSuccess = userDao.buyOneTicketToUser(ut);
        }

        return false;
    }
}
