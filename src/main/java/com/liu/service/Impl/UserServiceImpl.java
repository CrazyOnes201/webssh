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
    public User findUser(User user){
        return userDao.findUser(user);
    }

    //更新用户信息 admin+user
    public String updateUser(User user){
        if(userDao.updateUser(user).equals("success"))
            return "success";
        else
            return "error";
    }


}
