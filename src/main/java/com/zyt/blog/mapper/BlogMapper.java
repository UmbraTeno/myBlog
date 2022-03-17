package com.zyt.blog.mapper;


import com.zyt.blog.po.Blog;
import com.zyt.blog.vo.BlogQuery;
import com.zyt.blog.vo.CountBlogByTypeId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper
public interface BlogMapper {
    Integer save(Blog blog);   //新增分类

    Blog findById(@Param("id") Long id,@Param("published") Boolean published);      //根据id和是否发布查询博客

    //根据标题、类型、是否推荐、是否发布，查询所有博客
    List<Blog> findByPage(@Param("blog") BlogQuery blog,@Param("published") Boolean published);

    List<Blog> findByPublished (Boolean published);   //根据是否发布，查询所有博客

    List<Blog> findByQuery (String query);   //根据关键词，查询标题、内容中带有关键词的所有博客

    CountBlogByTypeId findByTypeId (Long typeId);   //根据typeId，查询所有已经发布博客的个数

    Integer updateById(Blog blog);     //根据id来更新博客

    Integer updateView(Blog blog);     //更新博客的访问数据

    Integer updateByTypeId(@Param("typeId")  Long typeId, @Param("updateTypeId") Long updateTypeId);

    void deleteById(Long id);   //根据id删除博客

    List<Blog> findByRecommendCreateTime(Integer size);   //根据创建时间降序查询size个推荐的已发布的blog

    List<Blog> findByTagId(@Param("tagId") Long tagId,@Param("published") Boolean published);    //根据tag的id和是否发布查询对应的blog

    List<String> findBlogYear(@Param("published") Boolean published);    //根据是否发布，获取所有博客的创建年份(去除重复)

    List<Blog> findByYear(@Param("year") String year,@Param("published")Boolean published ); //根据年份查询已经发布的博客

    Long findCountBlog(@Param("published") Boolean published);   //根据是否发布，查询博客的数量

}
