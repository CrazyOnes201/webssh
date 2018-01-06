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
    public List<List<Traininfo>> findTraininfoList(String beStation, String enStation) {
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

        List<List<Traininfo>> result = new ArrayList<List<Traininfo>>();
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
        if(result.size()>0) {
            return result.get(0);
        }
        return null;
    }

    /**
     * 返回所有的数据库中对应的Train类实体
     * @return 返回全部Train类实体
     */
    public Train getAllTrain(){
        String hql = "from Train where trainId='" + "G53" + "'";
        List<Train> result = (List<Train>)this.getHibernateTemplate().find(hql);
        if(result.size()>0) {
            return result.get(0);
        }
        return null;
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
//删除车次
    public String deleteTrain(Train train) {
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.delete(train);
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
//更新车次
    public String updateTrain(Train train) {
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.update(train);
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

    /**
     * 根据trainId获取traininfo表中所有对应车次的信息列表并按照rank字段排序
     * @param trainId 需要查询的trainId
     * @return 返回Traininfo类的列表
     */
    public List<Traininfo> getTraininfoListByTrainId(String trainId) {
        String hql = "from Traininfo where trainId='" + trainId + "' order by rank";
        List<Traininfo> result = (List<Traininfo>)this.getHibernateTemplate().find(hql);

        return result;
    }

    /**
     * 根据起始和目的站点查询中转换乘的车次方法表（只中转换乘一次）
     * @param beStation 需要中转的起始站点
     * @param tarStation 需要中转的目的站点
     * @return 包含有出发站和到达站列表的列表(列表以两个为一个完整中转结果)
     */
    public List<List<Traininfo>> findTransferTrain(String beStation, String tarStation) {
        String beHql = "from Traininfo where station='" + beStation
                + "' and trainId not in (select trainId from Traininfo where station='"
                + tarStation + "')";
        String tarHql = "from Traininfo where station='" + tarStation
                + "' and trainId not in (select trainId from Traininfo where station='"
                + beStation + "')";
        List<Traininfo> posBeTraininfoList = (List<Traininfo>)this.getHibernateTemplate().find(beHql);
        List<Traininfo> posTarTraininfoList = (List<Traininfo>)this.getHibernateTemplate().find(tarHql);
        int posBeListLength = posBeTraininfoList.size();
        int posTarListLength = posTarTraininfoList.size();
        List<Traininfo> beTraininfoResult = new ArrayList<Traininfo>();
        List<Traininfo> tarTraininfoResult = new ArrayList<Traininfo>();

        for(int i = 0; i < posBeListLength; ++i) {
            Traininfo beListElem = posBeTraininfoList.get(i);
            for(int j = 0; j < posTarListLength; ++j) {
                Traininfo tarListElem = posTarTraininfoList.get(j);
                String transferHql = "select atabel from Traininfo as atabel, Traininfo as btabel where "
                        + "atabel.trainId='" + beListElem.getTrainId() + "' and btabel.trainId='"
                        + tarListElem.getTrainId() + "' and atabel.station = btabel.station and "
                        + "atabel.startTime < btabel.startTime and atabel.rank > " + beListElem.getRank()
                        + " and btabel.rank < " + tarListElem.getRank();
                List<Traininfo> beTransferList = (List<Traininfo>)this.getHibernateTemplate().
                        find(transferHql);
                if(beTransferList.size() > 0) {
                    Traininfo beTransferTrain = beTransferList.get(0);
                    Traininfo tarTransferTrain = getTraininfoByTrainIdAndStation(tarListElem.getTrainId(),
                            beTransferTrain.getStation());
                    beTraininfoResult.add(beListElem);
                    beTraininfoResult.add(tarTransferTrain);
                    tarTraininfoResult.add(beTransferTrain);
                    tarTraininfoResult.add(tarListElem);
                }
            }
        }

        List<List<Traininfo>> result = new ArrayList<List<Traininfo>>();
        result.add(beTraininfoResult);
        result.add(tarTraininfoResult);

        return result;
    }

    /**
     * 根据车次的id和站点查询唯一对应的Traininfo类结果
     * @param trainId 需要查询的车次id
     * @param station 需要查询的车次站点
     * @return 根据条件查询到的唯一Traininfo类结果
     */
    public Traininfo getTraininfoByTrainIdAndStation(String trainId, String station) {
        String hql = "from Traininfo where trainId='" + trainId + "' and station='"
                + station + "'";
        List<Traininfo> result = (List<Traininfo>)this.getHibernateTemplate().find(hql);

        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }


}