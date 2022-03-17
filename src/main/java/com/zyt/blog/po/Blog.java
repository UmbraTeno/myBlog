package com.zyt.blog.po;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Blog {

    private Long id;    //博客id
    private String title;   //博客题目
    private String content; //博客内容
    private String firstPicture;     //博客首图
    private String flag;    //博客标记
    private Integer views;  //浏览次数
    private boolean appreciation;   //赞赏功能是否开启
    private boolean shareStatement; //转载声明是否开启
    private boolean commentabled;   //评论是否开启
    private boolean published;  //是否发布
    private boolean recommend; //是否推荐
    private Timestamp createTime;    //创建时间
    private Timestamp updateTime;    //更新时间
    private String description;     //博客描述
    private String tagIds;      //前端传来的标签组id
    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }


    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blog{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", firstPicture='").append(firstPicture).append('\'');
        sb.append(", flag='").append(flag).append('\'');
        sb.append(", views=").append(views);
        sb.append(", appreciation=").append(appreciation);
        sb.append(", shareStatement=").append(shareStatement);
        sb.append(", commentabled=").append(commentabled);
        sb.append(", published=").append(published);
        sb.append(", recommend=").append(recommend);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", description='").append(description).append('\'');
        sb.append(", tagIds='").append(tagIds).append('\'');
        sb.append(", type=").append(type);
        sb.append(", tags=").append(tags);
        sb.append(", user=").append(user);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}
