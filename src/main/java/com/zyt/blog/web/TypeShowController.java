package com.zyt.blog.web;

import com.zyt.blog.service.BlogService;
import com.zyt.blog.service.TypeService;
import com.zyt.blog.vo.BlogQuery;
import com.zyt.blog.vo.CountBlogByTypeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/type/{id}")
    public String types(@RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "5",name = "pageSize")Integer pageSize,
                        @PathVariable Long id, Model model){
        List<CountBlogByTypeId> types = typeService.listTypeTop(10000);
        if(id == -1){
            id = types.get(0).getTypeId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageNum,pageSize,blogQuery,true));
        model.addAttribute("activeTypeId",id);
        return "types";
    }


}
