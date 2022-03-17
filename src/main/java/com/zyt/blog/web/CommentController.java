package com.zyt.blog.web;

import com.zyt.blog.po.Comment;
import com.zyt.blog.po.User;
import com.zyt.blog.service.BlogService;
import com.zyt.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value("${comment.avatar}")
    private String avatar;

    //获取博客的评论信息，并发送给前端的commentList区域
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    //接受评论信息
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlogId();
        comment.setBlog(blogService.getBlog(blogId,true));
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
        }else{
            comment.setAvatar(avatar);

        }

        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }
}
