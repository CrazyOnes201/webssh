package com.liu.dao.Impl;

import com.liu.dao.UserDAO;
import com.liu.entity.Usedticket;
import com.liu.entity.User;
import org.hibernate.*;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    /**
     * 获取 currentSession
     *
     * @return Session
     */
    private Session getSession() {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
    //增加用户
    public void saveUser(User user){
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.save(user);
            tran.commit();
        } catch(HibernateException e) {
            e.printStackTrace();
            tran.rollback();
        } finally {
            session.close();
        }
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

    /**
     * 更新用户信息
     *
     * @param user 更新后的用户数据
     */
    public String updateUser(User user) {
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.clear();
            session.update(user);
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
     * 删除用户
     *
     * @param user 必须为完整用户数据
     */
    public String deleteUser(User user) {
        Session session= getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.delete(user);
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
     * 根据ut插入usedticket表中，返回插入结果
     * @param ut 用户的已购车票信息
     * @return 是否添加成功
     */
    public boolean buyOneTicketToUser(Usedticket ut) {
        Session session = getSession();
        session.setFlushMode(FlushMode.AUTO);
        Transaction tran=session.beginTransaction();
        try {
            session.save(ut);
            tran.commit();
            return true;
        } catch(HibernateException e) {
            e.printStackTrace();
            tran.rollback();
        }

        return false;
    }

}
