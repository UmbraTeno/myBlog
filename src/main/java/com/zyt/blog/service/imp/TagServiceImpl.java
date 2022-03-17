package com.zyt.blog.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyt.blog.NotFindException;


import com.zyt.blog.mapper.BlogTagMapper;
import com.zyt.blog.mapper.TagMapper;
import com.zyt.blog.po.Tag;
import com.zyt.blog.service.TagService;

import com.zyt.blog.vo.CountBlogByTagId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Transactional
    @Override
    public Integer saveTag(Tag tag) {
        return tagMapper.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.findById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.findTypeByName(name);
    }

    @Override
    public PageInfo<Tag> listTag(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //运行完上面的语句之后，会加强下一个查询，使下一个查询为分页查询
        List<Tag> tagList = tagMapper.findByPage();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tagList);
        return tagPageInfo;
    }

    @Override
    public List<CountBlogByTagId> listTagTop(Integer size) {
        //先查询出所有的tag
        List<Tag> tagList = tagMapper.findAll();
        //遍历集合，通过中间表查询对应tag的blog个数
        List<CountBlogByTagId> blogCount = new ArrayList<>();
        for(Tag t : tagList){
            CountBlogByTagId blogByTagId = blogTagMapper.findByTagId(t.getId());
            if (blogByTagId.getTagId() != null){
                blogCount.add(blogByTagId);
            }else{
                CountBlogByTagId countBlogByTagId = new CountBlogByTagId();
                countBlogByTagId.setTagId(t.getId());
                countBlogByTagId.setTagName(t.getName());
                countBlogByTagId.setBlogCount(0);
                blogCount.add(countBlogByTagId);
            }

        }
        //降序排列集合
        Collections.sort(blogCount,((o1, o2) -> o2.getBlogCount() - o1.getBlogCount()));
        //判断一下size的个数是否大于集合的size
        if (size > blogCount.size()){
            size = blogCount.size();
        }
        //返回size个type
        return blogCount.subList(0,size);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagMapper.findAllByList(convertToList(ids));
    }

    //字符串转数组
    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for (int i = 0;i < idarray.length;i++){
                list.add(Long.valueOf(idarray[i]));
            }
        }
        return list;
    }


    @Transactional
    @Override
    public Integer updateTag(Long id, Tag tag) {
        Tag t = tagMapper.findById(id);
        if (t == null) {
            throw new NotFindException("不存在该类型");
        }
        return tagMapper.updateById(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteById(id);
        //将所有博客中的改标签置为空标签（id为0的空标签）
        blogTagMapper.updateByTagId(id, 0L);
    }
}
