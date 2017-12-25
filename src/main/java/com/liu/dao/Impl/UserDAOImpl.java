package com.liu.dao.Impl;

import com.liu.dao.UserDAO;
import com.liu.entity.User;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
    //增加用户
    public void saveUser(User user){
        Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();

        session.save(user);
        tran.commit();
    }

    //查询验证用户是否存在
    public User findUser(User user){
        User firstuser = new User();
        //HQL查询语句
        String hql = "from User user where user.username='"
                + user.getUsername() + "' and user.password= '"
                + user.getPassword() + "'";
        //将查询出的结果放到List
        List<User> userlist = (List<User>) this.getHibernateTemplate().find(hql);
        //判断是否有查询结果，换句话说就是判断用户是否存在
        if(userlist.size()>0){
            //取出查询结果的第一个值，理论上数据库是没有重复的用户信息
            firstuser = userlist.get(0);
        }
        return firstuser;
    }
}
