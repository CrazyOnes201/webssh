//package com.liu.dao.Impl;
//
//import org.hibernate.FlushMode;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class SearchTicketDAOImpl {
//    //查找车票
//    public void SearchTicket(){
//        Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
//        session.setFlushMode(FlushMode.AUTO);
//        Transaction tran=session.beginTransaction();
//
//        session.save();
//        tran.commit();
//}