package com.liu.dao.Impl;

import com.liu.dao.TestDAO;
import com.liu.entity.Testtable;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * 测试uuid专用DAO层
 * Created by Crazy Ones on 2017/12/29.
 */
public class TestDAOImpl extends HibernateDaoSupport implements TestDAO {

    public void insert(int num) {
        Testtable tt = new Testtable();
        tt.setNum(num);
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran = session.beginTransaction();
        session.save(tt);
        tran.commit();
        // 注释掉就好了
        //session.close();
//        this.getHibernateTemplate().save(tt);
    }
}
