package com.zyt.blog.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyt.blog.NotFindException;
import com.zyt.blog.mapper.BlogMapper;
import com.zyt.blog.mapper.BlogTagMapper;
import com.zyt.blog.mapper.CommentMapper;
import com.zyt.blog.mapper.TagMapper;
import com.zyt.blog.po.Blog;
import com.zyt.blog.po.BlogTag;
import com.zyt.blog.po.Tag;
import com.zyt.blog.service.BlogService;
import com.zyt.blog.util.MarkDownUtils;
import com.zyt.blog.vo.BlogQuery;
import com.zyt.blog.vo.BlogTags;
import com.zyt.blog.vo.CountBlogByTagId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    @Override
    public Blog getBlog(Long id,Boolean published) {
        return blogMapper.findById(id,published);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id,Boolean published) {
        Blog blog = this.getBlog(id,published);
        if (blog == null) {
            throw new NotFindException("该博客不存在");
        }
        //将blog中的content转为html类型
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(blog.getContent()));
        blog.setViews(blog.getViews()+1);   //访问数量+1
        blogMapper.updateView(blog);    //更新一下博客的访问数据
        blogMapper.updateById(blog);
        return blog;
    }

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize, BlogQuery blog,Boolean published) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogMapper.findByPage(blog,published);
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize, Boolean published) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogMapper.findByPublished(published);
        return new PageInfo<>(blogList);
    }

    //根据关键词，查询带有关键词的博客
    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize, String query) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogMapper.findByQuery(query);
        return new PageInfo<>(blogList);
    }


    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize, Long tagId,Boolean published) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogMapper.findByTagId(tagId,published);
        for (Blog b : blogList){
            b.setTags(tagMapper.findByBlogId(b.getId()));
        }
        return new PageInfo<>(blogList);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return blogMapper.findByRecommendCreateTime(size);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog(Boolean published) {
        //先获取所有博客的年份
        List<String> blogYear = blogMapper.findBlogYear(published);
        Map<String,List<Blog>> map = new HashMap<>();
        for (String year : blogYear){
            map.put(year,blogMapper.findByYear(year,published));
        }
        return map;
    }

    @Override
    public Long countBlog(Boolean published) {
        return blogMapper.findCountBlog(published);
    }

    @Transactional
    @Override
    public Integer saveBlog(Blog blog) {
       //重置浏览次数为0
        blog.setViews(0);
        Integer save = blogMapper.save(blog);
        //获取blog中的tag集合，并将blog和tag的关系保存在临时表中
        Long blogId = blog.getId();
        List<Tag> tags = blog.getTags();
        for(Tag t : tags){
            blogTagMapper.save(blogId,t.getId());
        }
        return save;
    }

    @Transactional
    @Override
    public Integer updateBlog(Blog blog) {
        Blog b = blogMapper.findById(blog.getId(),null);
        if (b == null){
            throw new NotFindException("该博客不存在");
        }
        Long blogId = blog.getId();
        //先删除原有的blog和tag对应关系
        blogTagMapper.delete(blogId);
        //保存blog和tag的对应关系到中间表中
        List<Tag> tags = blog.getTags();
        for(Tag t : tags){
            blogTagMapper.save(blogId,t.getId());
        }
        return blogMapper.updateById(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteById(id);
        //删除t_blog_tag中blog与tag的对应关系
        blogTagMapper.delete(id);
        //删除博客对应的评论
        commentMapper.deleteByBlogId(id);
    }
}
