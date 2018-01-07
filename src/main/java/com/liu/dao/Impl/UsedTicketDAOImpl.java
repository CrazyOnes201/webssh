package com.liu.dao.Impl;

import com.liu.dao.UsedTicketDAO;
import com.liu.entity.Usedticket;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UsedTicketDAOImpl extends HibernateDaoSupport implements UsedTicketDAO {
    Session getSession() {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
    //查找某用户已经购买的票
    public List<Usedticket> findUsedTicket(int UserId){
        Usedticket usedticket = new Usedticket();
        //HQL查询语句
        String hql = "from Usedticket where userId='" + UserId + "'";
        List<Usedticket> list = (List<Usedticket>)this.getHibernateTemplate().find(hql);
        return list;

    }
}
