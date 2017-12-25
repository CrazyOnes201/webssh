package com.liu.service.Impl;

import com.liu.dao.UserDAO;
import com.liu.entity.User;
import com.liu.service.UserService;

public class SearchTicketImpl {
    private UserDAO userdao;

    public UserDAO getUserdao() {
        return userdao;
    }

    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

    //保存用户信息
    public boolean saveUser(User user){
        User firstuser = this.userdao.findUser(user);
        if(firstuser.getUsername()==null) {
            this.userdao.saveUser(user);
            return true;
        }
        return false;
    }
    //查找验证用户信息
    public boolean findUser(User user){
        //
        User firstuser = this.userdao.findUser(user);
        //在UserDAO查询中已经判断了只有当用户名和密码都存在时才返回firstuser
        //所以在这里只用判断firstuser里面用户名或者密码中的一个是否存在就可以了
        if(firstuser.getUsername()!=null){
            return true;
        }else{
            return false;
        }
    }
}
