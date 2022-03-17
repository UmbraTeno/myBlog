package com.zyt.blog.po;


//此类表示的是Bolg表和Tag表多对多的中间表
public class BlogTag {
   private Long blogId;   //博客id
   private Long tagId;  //标签id

    public BlogTag() {
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
