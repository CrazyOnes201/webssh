package com.liu.dao.Impl;

import com.liu.dao.DictDAO;
import com.liu.dao.TicketDAO;
import com.liu.entity.*;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Crazy Ones on 2017/12/30.
 */
public class TicketDAOImpl extends HibernateDaoSupport implements TicketDAO {
    @Autowired
    DictDAO dictDao;

    /**
     *  目的通过此函数完成对每日车票的更新
     *  ##但未完全完成##
     */
    public void insertEveryDayTicket() {
        /* 考虑封装 */
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        /* 逻辑开始 */
        Map trainMap = new HashMap();
        String trainHql =  "from Train";
        List<Train> trainResult = (List<Train>)this.getHibernateTemplate().find(trainHql);
        for(int i = 0; i < trainResult.size(); ++i) {
            Train elem = trainResult.get(i);
            trainMap.put(elem.getTrainId(), elem.getSeatType());
        }

        String traininfoHql = "from Traininfo";
        List<Traininfo> traininfoResult = (List<Traininfo>)this.getHibernateTemplate()
                .find(traininfoHql);
        for(int i = 0; i < traininfoResult.size(); ++i) {
            Traininfo elem = traininfoResult.get(i);
            String seatType = (String)trainMap.get(elem.getTrainId());
            String trainseattypeHql = "from TrainSeatType where type='" + seatType + "'";
            List<TrainSeatType> trainSeatTypeResult = (List<TrainSeatType>)this.getHibernateTemplate()
                    .find(trainseattypeHql);
            for(int j = 0; j < trainSeatTypeResult.size(); ++j) {
                Remainticket remainElem = new Remainticket();
                TrainSeatType seatTypeElem = trainSeatTypeResult.get(j);
                remainElem.setLevel(seatTypeElem.getLevel());
                remainElem.setNowNum(seatTypeElem.getNum());
                remainElem.setTraininfoId(elem.getId());
                /*
                * 考虑使用此方法设置日期
                * remainElem.setDate(tarDate);
                * */
                Transaction tran = session.beginTransaction();
                try {
                    session.save(remainElem);
                    tran.commit();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    tran.rollback();
                }
            }
        }
    }

    /**
     * 根据 车次号、起始站号、目的站号、出发日期查询车次的车票信息
     * ### 未完成对时间的查询 ###
     * @param trainId 车次号
     * @param beRank 起始站号
     * @param tarRank 目的站号
     * @return 查询车次的车票信息列表（未根据需要对level排序）
     */
    public ArrayList<Ticket> searchTicketInfo(String trainId, int beRank, int tarRank) {
        String traininfoHql = "from Traininfo where trainId='" + trainId
                + "' and rank between " + beRank + " and " + tarRank + " order by rank";
        List<Traininfo> traininfoList = (List<Traininfo>)this.getHibernateTemplate().find(traininfoHql);
        Map resultMap = new HashMap();
        for(int i = 0; i < traininfoList.size(); ++i) {
            String remainticketHql = "from Remainticket where traininfoId='"
                    + traininfoList.get(i).getId() + "'";                // 缺少指定日期
            List<Remainticket> remainticketList = (List<Remainticket>)this.getHibernateTemplate().
                    find(remainticketHql);
            for(int j = 0; j < remainticketList.size(); ++j) {
                Remainticket rt = remainticketList.get(j);
                if(resultMap.get(rt.getLevel()) == null) {
                    resultMap.put(rt.getLevel(), rt.getNowNum());
                } else {
                    if((Integer)resultMap.get(rt.getLevel()) > rt.getNowNum()) {
                        resultMap.put(rt.getLevel(), rt.getNowNum());
                    }
                }
            }
        }

        ArrayList<String> mapKeyList = new ArrayList<String>(resultMap.keySet());
        int miles = traininfoList.get(traininfoList.size() - 1).getMiles() -
                traininfoList.get(0).getMiles();
        ArrayList<Ticket> resultList = new ArrayList<Ticket>();
        int indexEmptySeat = -1;
        double emptySeatPrice = 0;
        for(int i = 0; i < mapKeyList.size(); ++i) {
            Ticket tElem = new Ticket();
            String key = mapKeyList.get(i);
            tElem.setLevel(key);
            tElem.setNum((Integer)resultMap.get(key));
            if(!key.equals("无座")) {
                int percent = Integer.parseInt(dictDao.findValueBySign(key));
                double price = miles * percent / 100.0;
                tElem.setPrice(price);
                if(key.equals("二等座") || key.equals("硬座")) {
                    emptySeatPrice = price;
                }
            } else {
                indexEmptySeat = i;
            }
            resultList.add(tElem);
        }

        if(indexEmptySeat != -1 && emptySeatPrice != 0) {
            resultList.get(indexEmptySeat).setPrice(emptySeatPrice);
        }

        return resultList;
    }

//    public


}