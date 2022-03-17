package com.zyt.blog.service;

import com.zyt.blog.po.Comment;

import java.util.List;

public interface CommentService {

    //获取博客中的评论
    List<Comment> listCommentByBlogId(Long blogId);

    //保存评论
    Integer saveComment(Comment comment);

}
