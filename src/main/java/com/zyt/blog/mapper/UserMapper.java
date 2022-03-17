package com.zyt.blog.mapper;

import com.zyt.blog.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //添加一个用户
    void save (User user);
    //根据id删除一个用户
    void delete (Long id);
    //根据id修改一个用户
    Integer update(User user);
    //根据id查询一个用户
    User findById(Long id);
    //查询所有用户
    List<User> findAll();
    //根据用户名和密码查询用户
    User findByUsernameAndPassword( @Param("username") String username, @Param("password") String password);
}
