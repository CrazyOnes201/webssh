package com.liu.dao.Impl;

import com.liu.dao.DictDAO;
import com.liu.entity.Tots_dict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Crazy Ones on 2018/1/2.
 */
public class DictDAOImpl extends HibernateDaoSupport implements DictDAO {
    /**
     * 根据sign查询表中对应的value值(String型)
     * @param sign 所需查询的sign
     * @return 表中sign对应的value
     */
    public String findValueBySign(String sign) {
        String hql = "from Tots_dict where sign='" + sign + "'";
        List<Tots_dict> list = (List<Tots_dict>)this.getHibernateTemplate().find(hql);
        return list.get(0).getValue();
    }
}
