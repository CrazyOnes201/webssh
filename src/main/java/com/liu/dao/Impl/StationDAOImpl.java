package com.liu.dao.Impl;

import com.liu.entity.Train;
import com.liu.entity.Traininfo;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;

public class StationDAOImpl extends HibernateDaoSupport implements com.liu.dao.StationDAO {
    Session getSession() {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    //添加车站
    public String addStation(ArrayList<Traininfo> list) {
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            for (Traininfo traininfo:list){
                session.save(traininfo);
            }
            tran.commit();
            return "success";
        } catch(HibernateException e) {
            e.printStackTrace();
            tran.rollback();
        } finally {
            session.close();
        }
        return "error";
    }
}
