package com.liu.dao.Impl;

import com.liu.dao.TrainDAO;
import com.liu.entity.Traininfo;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class TrainDAOImpl extends HibernateDaoSupport implements TrainDAO {
    public List<Traininfo> findTrain(String beStation, String enStation) {
        String hql = "from Traininfo where station='" + beStation
                + "' and trainId in ( select trainId from Traininfo "
                + "where station='" + enStation + "')";
        List<Traininfo> resultList = (List<Traininfo>)this.
                getHibernateTemplate().find(hql);
        return resultList;
    }
}