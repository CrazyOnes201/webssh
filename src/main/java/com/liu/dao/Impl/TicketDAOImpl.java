package com.liu.dao.Impl;

import com.liu.dao.DictDAO;
import com.liu.dao.TicketDAO;
import com.liu.entity.*;
import com.liu.util.DateUtil;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.*;

/**
 * Created by Crazy Ones on 2017/12/30.
 */
public class TicketDAOImpl extends HibernateDaoSupport implements TicketDAO {
    @Autowired
    DictDAO dictDao;

    private Session getSession() {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    /**
     * 通过此函数完成对目标日期车票的更新
     * @param tarDate 所需更新的目标日期
     */
    public void insertDayTicket(Date tarDate) {
        /* 考虑封装 */
        Session session = getSession();
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
            Transaction tran = session.beginTransaction();
            try {
                for(int j = 0; j < trainSeatTypeResult.size(); ++j) {
                    Remainticket remainElem = new Remainticket();
                    TrainSeatType seatTypeElem = trainSeatTypeResult.get(j);
                    remainElem.setLevel(seatTypeElem.getLevel());
                    remainElem.setNowNum(seatTypeElem.getNum());
                    remainElem.setTraininfoId(elem.getId());
                    remainElem.setDate(tarDate);
                    session.save(remainElem);

                }
                tran.commit();
            } catch(HibernateException e) {
                e.printStackTrace();
                tran.rollback();
            }
        }
    }

    /**
     * 根据 车次号、起始站号、目的站号、出发日期查询车次的车票信息
     * @param trainId 车次号
     * @param beRank 起始站号
     * @param tarRank 目的站号
     * @param tarDate 指定日期(字符串形式)
     * @return 查询车次的车票信息列表（未根据需要对level排序）
     */
    public ArrayList<Ticket> searchTicketInfo(String trainId, int beRank, int tarRank, String tarDate) {
        String traininfoHql = "from Traininfo where trainId='" + trainId
                + "' and rank between " + beRank + " and " + tarRank + " order by rank";
        List<Traininfo> traininfoList = (List<Traininfo>)this.getHibernateTemplate().find(traininfoHql);
        Map resultMap = new HashMap();
        for(int i = 0; i < traininfoList.size(); ++i) {
            String remainticketHql = "from Remainticket where traininfoId='"
                    + traininfoList.get(i).getId() + "' and date='" + tarDate + "'";
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

        if(!resultMap.isEmpty()) {
            ArrayList<String> mapKeyList = new ArrayList<String>(resultMap.keySet());
            int miles = traininfoList.get(traininfoList.size() - 1).getMiles() -
                    traininfoList.get(0).getMiles();
            ArrayList<Ticket> resultList = new ArrayList<Ticket>();
            int indexEmptySeat = -1;
            double emptySeatPrice = 0;
            for (int i = 0; i < mapKeyList.size(); ++i) {
                Ticket tElem = new Ticket();
                String key = mapKeyList.get(i);
                tElem.setLevel(key);
                tElem.setNum((Integer) resultMap.get(key));
                if (!key.equals("无座")) {
                    int percent = Integer.parseInt(dictDao.findValueBySign(key));
                    double price = miles * percent / 100.0;
                    tElem.setPrice(price);
                    if (key.equals("二等座") || key.equals("硬座")) {
                        emptySeatPrice = price;
                    }
                } else {
                    indexEmptySeat = i;
                }
                resultList.add(tElem);
            }

            if (indexEmptySeat != -1 && emptySeatPrice != 0) {
                resultList.get(indexEmptySeat).setPrice(emptySeatPrice);
            }

            return resultList;
        }

        return null;
    }

    /**
     * 根据车票等级、目标日期、购买车票经过的线路站点表购买车票并返回是否购买成功
     * @param level 车票等级
     * @param tarDate 目标日期
     * @param trainList 购买车票需要全部通过的Traininfo表
     * @return 返回是否购买成功
     */
    public Boolean buyOneTicket(String level, String tarDate, List<Traininfo> trainList) {
        Boolean isSuccess = true;
        String baseHql = "from Remainticket where level='" + level + "' and date='"
                + tarDate + "'";
        Session session = getSession();
        Transaction tran = session.beginTransaction();
        int length = trainList.size();
        for(int i = 0; i < length; ++i) {
            String findHql = baseHql + " and traininfoId='" + trainList.get(i).getId() + "'";
            List<Remainticket> rtList = (List<Remainticket>)this.getHibernateTemplate().
                    find(findHql);
            if(rtList.size() < 1) {
                tran.rollback();
                isSuccess = false;
                break;
            }
            Remainticket rtElem = rtList.get(0);
            if(rtElem.getNowNum() < 1) {
                tran.rollback();
                isSuccess = false;
                break;
            }
            rtElem.setNowNum(rtElem.getNowNum() - 1);
            session.update(rtElem);
        }
        if(isSuccess) {
            tran.commit();
        }

        return isSuccess;
    }

    /**
     * 根据level查询Tots_dict中有没有相同value字段，确定车票等级是否合法
     * @param level 所需查询的车票等级
     * @return 车票等级是否合法
     */
    public boolean isIllegalLevel(String level) {
        String hql = "from Tots_dict where value='" + level + "'";
        List<Tots_dict> tdList = (List<Tots_dict>)this.getHibernateTemplate();
        if(tdList.size() > 0) {
            return true;
        }

        return false;
    }

    /**
     * 检查日期对应的车票是否已经生成
     * @param checkDate 需要检查的日期
     * @return 是否生成票
     */
    public boolean ticketIsCreated(Date checkDate) {
        String sCheckDate = DateUtil.javaDateToSql(checkDate);
        String hql = "from Remainticket where date='" + sCheckDate + "'";
        List<Remainticket> reList = (List<Remainticket>)this.getHibernateTemplate().find(hql);
        if(reList.size() != 0) {
            return true;
        }

        return false;
    }

    /**
     * 根据传入的合法日期边界删除所有非法日期的车票
     * @param leftLimit 合法日期的左边界
     * @param rightLimit 合法日期的右边界
     * @return 是否删除成功
     */
    public boolean removeIllegalTicket(Date leftLimit, Date rightLimit) {
        String sLeftLimit = DateUtil.javaDateToSql(leftLimit);
        String sRightLimit = DateUtil.javaDateToSql(rightLimit);
        String illHql = "from Remainticket where date < '" + sLeftLimit + "' or date > '"
                + sRightLimit + "'";
        List<Remainticket> illList = (List<Remainticket>)this.getHibernateTemplate().find(illHql);
        int length = illList.size();
        Session session = getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran = session.beginTransaction();
        try {
            for (int i = 0; i < length; ++i) {
                session.delete(illList.get(i));
            }
            tran.commit();
            return true;
        } catch(HibernateException e) {
            e.printStackTrace();
            tran.rollback();
        }

        return false;
    }

}