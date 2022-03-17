package com.zyt.blog.po;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Type {

    private Long id;    //分类id
    @NotBlank(message = "分类名称不能为空")
    private String name;    //分类名
    private List<Blog> blogs = new ArrayList<>();
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Type{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
