package com.zyt.blog.web.admin;

import com.zyt.blog.po.Tag;


import com.zyt.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //展示所有tag类别
    @GetMapping("/tags")
    public String List(Integer pageNum, Integer pageSize, Model model){
        if (pageSize == null){
            pageSize = 3;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        model.addAttribute("page", tagService.listTag(pageNum,pageSize));
        return "admin/tags";
    }

    //新增tag
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    //tag修改页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    //保存tag
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Integer saveTag = tagService.saveTag(tag);
        if (saveTag == 0) {
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    //更新tag
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id,RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Integer t = tagService.updateTag(id, tag);
        if (t == 0) {
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    //删除tag
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
