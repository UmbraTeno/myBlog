package com.zyt.blog.service;

import com.github.pagehelper.PageInfo;
import com.zyt.blog.po.Blog;
import com.zyt.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id,Boolean published);  //根据id和是否发布查询博客

    Blog getAndConvert(Long id,Boolean published);    //根据id和是否发布，获取并将content内容转换成html格式

    //根据标题、类型、是否推荐、是否发布，查询所有博客
    PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize, BlogQuery blog,Boolean published);

    PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize,Boolean published);   //根据是否发布，查询所有博客

    PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize,String query);   //根据关键词query，查询所有博客

    PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize,Long tagId,Boolean published);    //根据tag的id和是否发布查询出所有带有此标签的博客

    List<Blog> listRecommendBlogTop(Integer size);   //按照时间降序，查询指定size个数个推荐博客

    Map<String ,List<Blog>> archiveBlog(Boolean published);  //根据是否发布，查询博客数据，按照年份归档

    Long countBlog(Boolean published);       //根据是否发布查询所有博客的数量

    Integer saveBlog(Blog blog);    //新增博客

    Integer updateBlog(Blog blog);  //根据id修改博客

    void deleteBlog(Long id);   //根据id删除博客

}
