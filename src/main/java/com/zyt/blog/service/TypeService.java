package com.zyt.blog.service;

import com.github.pagehelper.PageInfo;
import com.zyt.blog.po.Type;
import com.zyt.blog.vo.CountBlogByTypeId;

import java.util.List;


public interface TypeService {
    Integer saveType(Type type);   //新增分类

    Type getType(Long id);      //根据id查询type

    Type getTypeByName(String name);    //根据名称查询type

    PageInfo<Type> listType(Integer pageNum, Integer pageSize); //分页查询

    List<CountBlogByTypeId> listTypeTop(Integer size);  //按照type对应blog的数量，查询指定size个数个type

    List<Type> listType();  //查询所有数据

    Integer updateType(Long id, Type type);     //根据id来更新type

    void deleteType(Long id);   //根据id删除type

}
