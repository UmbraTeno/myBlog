package com.zyt.blog.mapper;

import com.zyt.blog.po.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //根据parentComment来查询评论
    Comment findByParentComment(Long parentComment);

    //根据blogId查询评论,返回的结果根据create_time升序排列
    List<Comment> findByBlogId(Long blogId);

    //保存评论
    Integer save(Comment comment);

    //根据博客id删除评论
    void deleteByBlogId(Long blogId);
}
