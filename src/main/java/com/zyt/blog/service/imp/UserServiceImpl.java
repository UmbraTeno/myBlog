package com.zyt.blog.service.imp;

import com.zyt.blog.mapper.UserMapper;
import com.zyt.blog.po.User;
import com.zyt.blog.service.UserService;
import com.zyt.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
