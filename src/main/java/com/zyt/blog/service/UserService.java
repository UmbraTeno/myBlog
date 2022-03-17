package com.zyt.blog.service;

import com.zyt.blog.po.User;

public interface UserService {

    User checkUser(String username,String password);
}
