package com.zyt.blog.web;


import com.zyt.blog.service.BlogService;
import com.zyt.blog.service.TagService;
import com.zyt.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "8",name = "pageSize")Integer pageSize,
                        @RequestParam(defaultValue = "true",name="published")Boolean published, Model model){
        model.addAttribute("page",blogService.listBlog(pageNum, pageSize, published));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }

    //全局查询
    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "8",name = "pageSize")Integer pageSize,
                         @RequestParam("query") String query,
                         Model model){
        model.addAttribute("page",blogService.listBlog(pageNum,pageSize,query));
        model.addAttribute("query",query);
        return "search";
    }

    //展示详细的博客页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id,true));
        return "blog";
    }

    //底部的最新博客展示
    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){
        model.addAttribute("newBlogs",blogService.listRecommendBlogTop(3));
        return "_fragments :: newBlogList";
    }
}
