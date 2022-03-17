package com.zyt.blog.web;

import com.zyt.blog.po.BlogTag;
import com.zyt.blog.service.BlogService;
import com.zyt.blog.service.TagService;
import com.zyt.blog.vo.BlogQuery;
import com.zyt.blog.vo.CountBlogByTagId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TagShowController {

    @Autowired
    private TagService TagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String Tags(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "5",name = "pageSize")Integer pageSize,
                        @PathVariable Long id, Model model){
        List<CountBlogByTagId> Tags = TagService.listTagTop(10000);
        if(id == -1){
            id = Tags.get(0).getTagId();
        }
        model.addAttribute("tags",Tags);
        model.addAttribute("page",blogService.listBlog(pageNum,pageSize,id,true));
        model.addAttribute("activeTagId",id);
        return "tags";
    }


}
