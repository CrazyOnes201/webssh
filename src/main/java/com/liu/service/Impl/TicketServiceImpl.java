package com.liu.service.Impl;

import com.liu.dao.TicketDAO;
import com.liu.dao.TrainDAO;
import com.liu.dao.UserDAO;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Traininfo;
import com.liu.entity.Usedticket;
import com.liu.entity.User;
import com.liu.service.TicketService;
import com.liu.util.OperateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketDAO ticketDao;
    @Autowired
    TrainDAO trainDao;
    @Autowired
    UserDAO userDao;

    public boolean buyTicket(TrainAndTicket buytat, User user) {
        String level = "";  //是否合法
        Date tarDate = new Date();
        boolean isSuccess = false;

        List<Traininfo> tarTraininfoList = trainDao.selectTraininfoListByStation(buytat.getTrainId(),
                buytat.getBeginStation(), buytat.getTargetStation());
        if(tarTraininfoList != null &&
                ticketDao.buyOneTicket(buytat.getTicketList().get(0).getLevel(), buytat.getsDate(), tarTraininfoList)) {
            Usedticket ut = new Usedticket();
            OperateEntity.tatToUsedticket(buytat, ut);
            ut.setUserId(user.getUserId());
            isSuccess = userDao.buyOneTicketToUser(ut);
        }

        return isSuccess;
    }
}
