package com.liu.dao.Impl;

import com.liu.dao.TrainDAO;
import com.liu.entity.Train;
import com.liu.entity.Traininfo;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class TrainDAOImpl extends HibernateDaoSupport implements TrainDAO {

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
}