package com.zyt.blog.vo;

public class BlogQuery {

    private String title;
    private Long typeId;
    private Boolean recommend;

    public BlogQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlogQuery{");
        sb.append("title='").append(title).append('\'');
        sb.append(", typeId=").append(typeId);
        sb.append(", recommend=").append(recommend);
        sb.append('}');
        return sb.toString();
    }
}
