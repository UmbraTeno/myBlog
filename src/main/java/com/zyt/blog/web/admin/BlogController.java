package com.zyt.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.zyt.blog.po.Blog;
import com.zyt.blog.po.User;
import com.zyt.blog.service.BlogService;
import com.zyt.blog.service.TagService;
import com.zyt.blog.service.TypeService;
import com.zyt.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    //博客管理首页
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "5")Integer pageSize,
                        BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        PageInfo<Blog> blogPageInfo = blogService.listBlog(pageNum, pageSize, blog,null);
        model.addAttribute("page",blogPageInfo);
        return LIST;
    }

    //博客查询
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "page") Integer pageNum,
                         @RequestParam(defaultValue = "5") Integer pageSize,
                         BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageNum,pageSize,blog,null));
        return "admin/blogs :: blogList";
    }

    //跳转到发布博客页面
    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    //在model中添加数据库中的type和tag数据
    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    //跳转到更新博客页面
    @GetMapping("/blogs/{id}/input")
    public String ediInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id,null);
        if (blog != null) {
            blog.init();    ////将tags集合转换为tagIds字符串
        }else{

        }
        model.addAttribute("blog",blog);
        return INPUT;
    }

    //接收博客
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        //
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Integer saveBlog = 0;
        if (blog.getId() == null) {   //id为空，则为新增
            saveBlog = blogService.saveBlog(blog);
        } else {
            saveBlog = blogService.updateBlog(blog);
        }
        if (saveBlog == 0) {
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

    //底部的最新博客展示
    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){
        model.addAttribute("newBlogs",blogService.listRecommendBlogTop(3));
        return "admin/_fragments :: newBlogList";
    }


}
