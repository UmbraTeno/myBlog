package com.zyt.blog.po;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {

    private Long id;    //评论id
    private String nickname;    //昵称
    private String email;   //邮箱
    private String content; //评论内容
    private String avatar;  //头像
    private Timestamp createTime;    //创建时间
    private Long blogId;    //评论所在的博客的id
    private Boolean adminComment;   //是否是管理员评论
    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();    //多个子类评论
    private Comment parentComment;  //父类评论

    public Comment() {
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }
}
