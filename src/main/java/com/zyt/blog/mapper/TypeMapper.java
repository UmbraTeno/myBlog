package com.zyt.blog.mapper;

import com.zyt.blog.po.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TypeMapper {
    Integer save(Type type);   //新增分类

    Type findById(Long id);      //根据id查询type

    Type findTypeByName(String name);    //根据名称查询type

    List<Type> findAll(); //查询所有

    Integer updateById(Type type);     //根据id来更新type

    void deleteById(Long id);   //根据id删除type
}
