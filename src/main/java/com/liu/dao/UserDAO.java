package com.liu.dao;

import com.liu.entity.Usedticket;
import com.liu.entity.User;

public interface UserDAO {
    //声明增加和查找方法
    public void saveUser(User user);

    public User findUser(User user);
    public String updateUser(User user);
    public String deleteUser(User user);
    public boolean buyOneTicketToUser(Usedticket ut);

}
