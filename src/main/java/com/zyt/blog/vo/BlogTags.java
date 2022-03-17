package com.zyt.blog.vo;
//描述了博客与标签的对应关系
public class BlogTags {
    private Long blogId;    //博客id
    private String tags;    //博客id对应的tag的字符串

    public BlogTags() {
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlogTags{");
        sb.append("blogId=").append(blogId);
        sb.append(", tags='").append(tags).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
