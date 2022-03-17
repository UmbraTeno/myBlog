package com.zyt.blog.vo;

//该类表示type的id对应的blog数量
public class CountBlogByTypeId {
    private Long typeId;
    private String typeName;
    private Integer blogCount;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public CountBlogByTypeId() {
    }
}
