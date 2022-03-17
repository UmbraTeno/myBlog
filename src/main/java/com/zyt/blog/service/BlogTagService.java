package com.zyt.blog.service;

import com.zyt.blog.po.BlogTag;


public interface BlogTagService {
    //添加一个blog和tag的对应关系
    Integer save (Long blogId,Long tagId);
    //根据blogId删除一篇博客对应标签的记录
    Integer delete (Long blogId);
    //根据博客id先全删除再把更新后的博客插入
    Integer update(BlogTag blogTag);
    //根据id查询一个篇博客以及对应的标签
    BlogTag findById(Long blogId,Long tagId);
}
