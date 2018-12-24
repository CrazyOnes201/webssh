package com.liu.dao.Impl;

import com.liu.entity.Train;
import com.liu.entity.Traininfo;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

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
                if(findStation(traininfo)==null) {
                    session.save(traininfo);
                }
                else
                {
                    tran.rollback();
                    return "error";
                }

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

    //查找车站
    public Traininfo findStation(Traininfo traininfo){
        Traininfo traininfo1 =new Traininfo();
        //HQL查询语句
        String hql = "from Traininfo where trainId='"
                + traininfo.getTrainId()+ "' and station= '"
                + traininfo.getStation()+ "'";
        //将查询出的结果放到List
        System.out.println(traininfo.getTrainId());
        System.out.println(traininfo.getStation());
        List<Traininfo> trainlist = (List<Traininfo>) this.getHibernateTemplate().find(hql);
        //判断是否有查询结果，换句话说就是判断用户是否存在
        if(trainlist.size()>0){
            //取出查询结果的第一个值，理论上数据库是没有重复的用户信息
            traininfo1 = trainlist.get(0);
            return traininfo1;
        }
        return null;

    }

    public String deleteStation(Traininfo traininfo){
        System.out.println("trainfo==null");
        System.out.println(traininfo.getTrainId());
        System.out.println(traininfo.getStation());
        System.out.println(findStation(traininfo).getTrainId());
        System.out.println(findStation(traininfo).getStation());
        Traininfo traininfo1 = findStation(traininfo);
        if(traininfo1!=null){
            System.out.println("trainfo!=null");
            Session session= getSession();
            session.setFlushMode(FlushMode.AUTO);
            Transaction tran=session.beginTransaction();
            try {
                session.delete(traininfo1);
                tran.commit();
            } catch(HibernateException e) {
                e.printStackTrace();
                tran.rollback();
            } finally {
                session.close();
            }
            return "success";
        }
        else
            return "error";


    }


}
