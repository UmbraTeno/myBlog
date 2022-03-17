package com.zyt.blog.service.imp;

import com.zyt.blog.mapper.BlogTagMapper;
import com.zyt.blog.po.BlogTag;
import com.zyt.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;


    @Override
    public Integer save(Long blogId, Long tagId) {
        return blogTagMapper.save(blogId,tagId);
    }

    @Override
    public Integer delete(Long blogId) {
        return blogTagMapper.delete(blogId);
    }

    @Override
    public Integer update(BlogTag blogTag) {
        return blogTagMapper.update(blogTag.getBlogId(),blogTag.getTagId());
    }

    @Override
    public BlogTag findById(Long blogId,Long tagId) {
        return blogTagMapper.findByBlogIdTagId(blogId,tagId);
    }

}
