package com.zyt.blog.service;

import com.github.pagehelper.PageInfo;
import com.zyt.blog.po.Tag;
import com.zyt.blog.vo.CountBlogByTagId;


import java.util.List;


public interface TagService {
    Integer saveTag(Tag tag);   //新增标签

    Tag getTag(Long id);      //根据id查询tag

    Tag getTagByName(String name);    //根据名称查询tag

    PageInfo<Tag> listTag(Integer pageNum, Integer pageSize); //分页查询

    List<CountBlogByTagId> listTagTop(Integer size);  //按照tag对应blog的数量，查询指定size个数个tag

    List<Tag> listTag();  //查询所有数据

    List<Tag> listTag(String ids);  //根据id字符串查询tag

    Integer updateTag(Long id, Tag tag);     //根据id来更新tag

    void deleteTag(Long id);   //根据id删除tag

}
