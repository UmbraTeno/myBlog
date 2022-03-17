package com.zyt.blog.mapper;


import com.zyt.blog.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TagMapper {
    Integer save(Tag tag);   //新增分类

    Tag findById(Long id);      //根据id查询tag

    List<Tag> findByBlogId(Long BlogId);     //根据blog的id查询对应tag

    Tag findTypeByName(String name);    //根据名称查询tag

    List<Tag> findByPage(); //分页查询

    List<Tag>findAll();    //查询所有

    List<Tag>findAllByList(@Param("list")List<Long> list);    //根据数组查询所有

    Integer updateById(Tag tag);     //根据id来更新tag

    void deleteById(Long id);   //根据id删除tag
}
