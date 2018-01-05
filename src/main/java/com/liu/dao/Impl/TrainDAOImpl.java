package com.liu.dao.Impl;

import com.liu.dao.TrainDAO;
import com.liu.entity.Train;
import com.liu.entity.Traininfo;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class TrainDAOImpl extends HibernateDaoSupport implements TrainDAO {
    Session getSession() {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
    /**
     * 根据出发站和目的站查找对应车次的出发和目的站点的信息
     * @param beStation 出发站名称
     * @param enStation 目的站名称
     * @return 返回出发站信息列表和目的站信息列表组成的列表
     */
    public ArrayList<List<Traininfo>> findTraininfoList(String beStation, String enStation) {
        String hql1 = "from Traininfo where station='" + beStation
                + "' and trainId in ( select trainId from Traininfo "
                + "where station='" + enStation + "')";
        List<Traininfo> startTrain = (List<Traininfo>)this.
                getHibernateTemplate().find(hql1);
        String hql2 = "from Traininfo where station='" + enStation
                + "' and trainId in ( select trainId from Traininfo "
                + "where station='" + beStation + "')";
        List<Traininfo> endTrain = (List<Traininfo>)this.
                getHibernateTemplate().find(hql2);
        for(int i = 0; i < startTrain.size(); ++i) {
            if(startTrain.get(i).getRank() > endTrain.get(i).getRank()){
                startTrain.remove(i);
                endTrain.remove(i);
                --i;
            }
        }

        ArrayList<List<Traininfo>> result = new ArrayList<List<Traininfo>>();
        result.add(startTrain);
        result.add(endTrain);

        return result;
    }

    /**
     * 根据trainId返回数据库中对应的Train类实体
     * @param trainId 所需查询的trainId
     * @return 返回 trainId对应的Train类实体
     */
    public Train getTrainById(String trainId) {
        String hql = "from Train where trainId='" + trainId + "'";
        List<Train> result = (List<Train>)this.getHibernateTemplate().find(hql);

        return result.get(0);
    }
    /**
     * 返回所有的数据库中对应的Train类实体
     * @return 返回全部Train类实体
     */
//    public ArrayList<List<Traininfo>> findTraininfoList(String beStation, String enStation) {
    public Train getAllTrain(){
        String hql = "from Train";
        List<Train> result = (List<Train>)this.getHibernateTemplate().find(hql);

        return result.get(0);
    }
//添加车次
    public String addTrain(Train train){
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.save(train);
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
//
//    //增加用户
//    public void saveUser(User user){
//        Session session= getSession();
//        session.setFlushMode(FlushMode.AUTO);
//        Transaction tran=session.beginTransaction();
//        try {
//            session.save(user);
//            tran.commit();
//        } catch(HibernateException e) {
//            e.printStackTrace();
//            tran.rollback();
//        } finally {
//            session.close();
//        }
//    }
//
//    //查询验证用户是否存在
//    public Train findTrain(Train train){
//        Train firsttrain = new Train();
//        //HQL查询语句
//        String hql = "from User where user.username='"
//                + user.getUsername() + "' and user.password= '"
//                + user.getPassword() + "'";
//        //将查询出的结果放到List
//        List<User> userlist = (List<User>) this.getHibernateTemplate().find(hql);
//        //判断是否有查询结果，换句话说就是判断用户是否存在
//        if(userlist.size()>0){
//            //取出查询结果的第一个值，理论上数据库是没有重复的用户信息
//            firstuser = userlist.get(0);
//        }
//        return firstuser;
//    }




}