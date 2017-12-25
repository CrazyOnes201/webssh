package com.liu.service;

import com.liu.entity.User;

public interface UserService {
    //声明增加和查找方法
    public boolean saveUser(User user);

    public User findUser(User user);
}
