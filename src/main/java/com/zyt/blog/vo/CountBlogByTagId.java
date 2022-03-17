package com.zyt.blog.vo;

//该类表示type的id对应的blog数量
public class CountBlogByTagId {
    private Long tagId;
    private String tagName;
    private Integer blogCount;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public CountBlogByTagId() {
    }
}
