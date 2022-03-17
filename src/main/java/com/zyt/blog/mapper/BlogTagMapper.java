package com.zyt.blog.mapper;

import com.zyt.blog.po.BlogTag;
import com.zyt.blog.vo.BlogTags;
import com.zyt.blog.vo.CountBlogByTagId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogTagMapper {
    //添加一个blog和tag的对应关系
    Integer save (@Param("blogId") Long blogId, @Param("tagId") Long tagId);
    //根据blogId删除一篇博客对应标签的记录
    Integer delete (@Param("blogId") Long blogId);
    //根据博客id和标签id更新标签id
    Integer update(@Param("blogId") Long blogId, @Param("tagId") Long tagId);
    //根据标签id修改对应博客的标签id
    Integer updateByTagId(@Param("tagId")Long tagId,@Param("updateTagId") Long updateTagId);
    //根据博客id和标签id查询一个篇博客以及对应的标签
    BlogTag findByBlogIdTagId(@Param("blogId") Long blogId, @Param("tagId") Long tagId);
    //根据博客id查询一个篇博客以及对应的标签字符串
    BlogTags findByBlogId(@Param("blogId") Long blogId);
    //根据tag的id查询出对应已发布的blog_id的个数
    CountBlogByTagId findByTagId(Long tagId);
    //根据tag的id查询出对应的blog的id
    List<Long> findBlogIdByTagId(Long tagId);
}
