package com.liu.service.Impl;

import com.liu.dao.UserDAO;
import com.liu.entity.User;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;

    //保存用户信息
    public boolean saveUser(User user){
        User firstuser = this.userDao.findUser(user);
        if(firstuser.getUsername()==null) {
            this.userDao.saveUser(user);
            return true;
        }
        return false;
    }
    //查找验证用户信息
    public boolean findUser(User user){
        //
        User firstuser = this.userDao.findUser(user);
        //在UserDAO查询中已经判断了只有当用户名和密码都存在时才返回firstuser
        //所以在这里只用判断firstuser里面用户名或者密码中的一个是否存在就可以了
        if(firstuser.getUsername()!=null){
            return true;
        }else{
            return false;
        }
    }
}
