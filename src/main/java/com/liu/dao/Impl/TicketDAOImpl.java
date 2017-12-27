package com.liu.dao.Impl;

import com.liu.dao.TicketDAO;
import com.liu.entity.Trainstation;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class TicketDAOImpl extends HibernateDaoSupport implements TicketDAO {
    public List<Trainstation> findTrain(String beStation, String enStation) {
        String hql = "from TrainStation where station='" + beStation
                + "' and trainId in ( select trainId from TrainStation "
                + "where station='" + enStation + "')";
        List<Trainstation> resultList = (List<Trainstation>)this.
                getHibernateTemplate().find(hql);
        return resultList;
    }
}
