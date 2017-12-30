package com.liu.dao.Impl;

import com.liu.dao.TicketDAO;
import com.liu.entity.Remainticket;
import com.liu.entity.Train;
import com.liu.entity.TrainSeatType;
import com.liu.entity.Traininfo;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Crazy Ones on 2017/12/30.
 */
public class TicketDAOImpl extends HibernateDaoSupport implements TicketDAO {
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
}