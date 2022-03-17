package com.zyt.blog.service.imp;

import com.zyt.blog.mapper.CommentMapper;
import com.zyt.blog.po.Comment;
import com.zyt.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    private final List<Comment> temporaryComments = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.findByBlogId(blogId);
        return eachComment(comments);
    }

    //处理评论的父子评论
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> replyComments = new ArrayList<>();    //二级子评论
        List<Comment> parentComments = new ArrayList<>(); //一级父评论
        Map<Long,List<Comment>> relation = new HashMap<>(); //描述父子评论的关系
        //筛选评论
        for (Comment c : comments){
            if (c.getParentComment() != null){
                replyComments.add(c);
            }else{
                parentComments.add(c);
            }
                relation.put(c.getId(),new ArrayList<>());
        }

        //遍历子评论，给每个父评论寻找子评论
        for (Comment c : replyComments){
           relation.get(c.getParentComment().getId()).add(c);
        }
        //遍历一级父评论，给每个一级评论添加子评论
        for (Comment comment : parentComments){
            if (relation.get(comment.getId()).size() > 0) {
                //递归调用添加所有的子评论
                List<Comment> commentList = new ArrayList<>();
                commentList.addAll(addAllSubset(comment, relation));
                comment.setReplyComments(commentList);
                temporaryComments.clear();
            }
        }

        return parentComments;
    }

    //递归给评论添加子评论
    private List<Comment> addAllSubset(Comment commentPresent,Map<Long,List<Comment>> relation) {
        Long id = commentPresent.getId(); //当前评论的id
        List<Comment> comments = relation.get(id);  //当前评论的子评论集合
        //当前评论的子评论
        for (Comment c : comments){
            //将子评论的父评论设置为当前评论
            c.setParentComment(commentPresent);
            //将子评论集合添加到当前评论中
            temporaryComments.add(c);
            //判断一下是否是最后一个子评论了
            if (relation.get(c.getId()).size() > 0){
                //将子评论替换掉当前评论(理论上来说子评论只有一个)
                addAllSubset(c,relation);
            }
        }

        return temporaryComments;
    }

    @Transactional
    @Override
    public Integer saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        //判断该评论是否是子评论，如果不是则将父评论设置为null
        if (parentCommentId != -1){
            comment.setParentComment(commentMapper.findByParentComment(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        return commentMapper.save(comment);
    }
}
