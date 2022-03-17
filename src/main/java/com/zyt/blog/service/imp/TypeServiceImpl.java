package com.zyt.blog.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyt.blog.NotFindException;
import com.zyt.blog.mapper.BlogMapper;
import com.zyt.blog.mapper.TypeMapper;
import com.zyt.blog.po.Type;
import com.zyt.blog.service.TypeService;
import com.zyt.blog.vo.CountBlogByTypeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private BlogMapper blogMapper;


    @Transactional
    @Override
    public Integer saveType(Type type) {
        return typeMapper.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.findById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.findTypeByName(name);
    }

    @Override
    public PageInfo<Type> listType(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //运行完上面的语句之后，会加强下一个查询，使下一个查询为分页查询
        List<Type> typeList = typeMapper.findAll();
        PageInfo<Type> typePageInfo = new PageInfo<>(typeList);
        return typePageInfo;
    }

    //按照type对应blog的数量，查询指定size个数个type
    @Override
    public List<CountBlogByTypeId> listTypeTop(Integer size) {
        //先查询出所有的type
        List<Type> typeList = typeMapper.findAll();
        //遍历集合，查询对应type的blog个数
        List<CountBlogByTypeId> blogCount = new ArrayList<>();
        for(Type t : typeList){
            CountBlogByTypeId countBlogByTypeId = blogMapper.findByTypeId(t.getId());
            if (countBlogByTypeId.getTypeId() != null){
                blogCount.add(countBlogByTypeId);
            }else{
                CountBlogByTypeId blogByTypeId = new CountBlogByTypeId();
                blogByTypeId.setTypeId(t.getId());
                blogByTypeId.setTypeName(t.getName());
                blogByTypeId.setBlogCount(0);
                blogCount.add(blogByTypeId);
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
    public List<Type> listType() {
        return typeMapper.findAll();
    }


    @Transactional
    @Override
    public Integer updateType(Long id, Type type) {
        Type t = typeMapper.findById(id);
        if (t == null) {
            throw new NotFindException("不存在该类型");
        }
        return typeMapper.updateById(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteById(id);
        //将所有博客中的该类别置为空类别（id为0的空类别）
        blogMapper.updateByTypeId(id,0L);
    }
}
