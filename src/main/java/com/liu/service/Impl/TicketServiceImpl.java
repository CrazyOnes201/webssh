package com.liu.service.Impl;

import com.liu.dao.TicketDAO;
import com.liu.dao.TrainDAO;
import com.liu.dao.UserDAO;
import com.liu.entity.TrainAndTicket;
import com.liu.entity.Traininfo;
import com.liu.entity.Usedticket;
import com.liu.entity.User;
import com.liu.service.TicketService;
import com.liu.util.DateUtil;
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

    /**
     * 根据需要购买的车票和购买车票的用户进行一系列验证后返回购买结果
     * @param buytat 需要购买的目标车票
     * @param user 购买车票的用户
     * @return 是否购买成功
     */
    public boolean buyTicket(TrainAndTicket buytat, User user) {
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

    /**
     * 根据开放售票天数 产生新票并删除非法日期车票
     * @param dayNum 开放售票天数
     * @return 返回是否执行成功
     */
    public boolean refreshTicket (int dayNum) {
        try {
            Date nowDate = DateUtil.getNowDateWithoutTime();
            for (int i = 0; i < dayNum; ++i) {
                Date elemDate = DateUtil.getSomeDaysDate(nowDate, i);
                if (!ticketDao.ticketIsCreated(elemDate)) {
                    ticketDao.insertDayTicket(elemDate);
                }
            }
            Date rightLimit = DateUtil.getSomeDaysDate(nowDate, dayNum - 1);
            ticketDao.removeIllegalTicket(nowDate, rightLimit);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
